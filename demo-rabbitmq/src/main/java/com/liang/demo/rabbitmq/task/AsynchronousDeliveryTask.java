package com.liang.demo.rabbitmq.task;

import com.rabbitmq.client.AMQP;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.support.DefaultMessagePropertiesConverter;
import org.springframework.amqp.rabbit.support.MessagePropertiesConverter;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 异步补偿消息策略
 * Created by lianghaofei on 2017/12/9.
 */
@Component
public class AsynchronousDeliveryTask {
    private static Logger logger = LoggerFactory.getLogger(AsynchronousDeliveryTask.class);

    private static TaskStatus status = TaskStatus.CLOSED;
    private static String LOST_MSG_FILE_PATH = "/data/logs/rabbitmq/";
    private static String LOST_MSG_FILE_SUFFIX = "_lost.txt";
    private MessagePropertiesConverter messagePropertiesConverter = new DefaultMessagePropertiesConverter();
    private String encoding = "UTF-8";

    public synchronized void resend() {
        if (status == TaskStatus.CLOSED) {
            status = TaskStatus.STARTED;
            initTask();
        }
    }

    /**
     * 异步将消息重写入rabbitmq
     */
    private void initTask() {
        ScheduledExecutorService resendService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("amq-async-resend-task").daemon(true).build());
        resendService.scheduleAtFixedRate(new Runnable() {
            public void run() {
                resendMessageToAmq();
            }
        }, 1, 3, TimeUnit.MINUTES);
    }

    /**
     * 重发消息接口
     */
    public void resendMessageToAmq() {
        File rootDir = new File(LOST_MSG_FILE_PATH);
        if (isDirectory(rootDir)) {
            File[] filelist = rootDir.listFiles();
            for (File messageDir : filelist) {
                if (isDirectory(messageDir)) {
                    File[] messageFileNames = messageDir.listFiles();
                    for (File messageFile : messageFileNames) {
                        //需要处理的文件以lost.txt结尾
                        if (!messageFile.getName().endsWith(LOST_MSG_FILE_SUFFIX)) {
                            continue;
                        }
                        String completeFileName = messageFile.getAbsolutePath();
                        Message message = readObjectFromFile(completeFileName);
                        if (message != null) {
                            boolean isSuccess = sendMessage(message);
                            if (isSuccess){
                                deleteFile(completeFileName);
                            }else {
                                //如果channel断开，则return;
                                return;
                            }
                        }
                    }
                }
                deleteDirectory(messageDir.getAbsolutePath());
            }
        }
    }

    /**
     * 读取指定文件中的序列化消息
     *
     * @param path
     * @return
     */
    private Message readObjectFromFile(String path) {
        ObjectInputStream is = null;
        Message message = null;
        try {
            is = new ObjectInputStream(new FileInputStream(path));
            message = (Message) is.readObject();// 从流中读取List的数据
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return message;
    }

    /**
     * 发送消息
     *
     * @param message
     */
    private boolean sendMessage(Message message) {
        MessageProperties messageProperties = message.getMessageProperties();
        String exchangeName = "";//messageProperties.getPublishExchange();
        String routingKey = "";//messageProperties.getPublishRoutingKey();
        boolean mandatory = true;

        AMQP.BasicProperties convertedMessageProperties = this.messagePropertiesConverter
                .fromMessageProperties(messageProperties, this.encoding);
       /* try {
            if (channel.isOpen()) {
                channel.basicPublish(exchangeName, routingKey, mandatory, convertedMessageProperties,
                        message.getBody());
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            logger.error("复写时出现异常", e);
            return false;
        }*/
        //TODO  模拟发送失败
        if ((new String(message.getBody())).equals("xiaxia")) {
            System.out.println("发送异常");
            return false;
        }else {
            System.out.println("发送消息成功");
            return true;
        }
    }

    /**
     * 删除单个文件
     *
     * @param fileName 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + fileName + "成功！");
                return true;
            } else {
                System.out.println("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            System.out.println("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }

    /**
     * 删除目录及目录下的文件
     *
     * @param dir 要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String dir) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator)) {
            dir = dir + File.separator;
        }
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            System.out.println("删除目录失败：" + dir + "不存在！");
            return false;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            //如果当前文件夹中还有未消费的文件，则不能删除
            if (files[i].getName().endsWith(LOST_MSG_FILE_SUFFIX)) {
                break;
            }
            // 删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
            // 删除子目录
            else if (files[i].isDirectory()) {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
        }
        if (!flag) {
            System.out.println("删除目录失败！");
            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {
            System.out.println("删除目录" + dir + "成功！");
            return true;
        } else {
            return false;
        }
    }

    private boolean isDirectory(File file) {
        if (file != null && file.exists() && file.isDirectory()) {
            return true;
        }
        return false;
    }


    enum TaskStatus {
        STARTED, CLOSED
    }

    public static void main(String[] args) {
//        new AsynchronousDeliveryTask().resend();
        new AsynchronousDeliveryTask().resendMessageToAmq();
    }

}
