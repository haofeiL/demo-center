package com.liang.demo.rabbitmq.task;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;

import java.io.*;
import java.util.Random;

/**
 * Created by haofeiL on 2017/12/10.
 */
public class SynchronousStorageStrategy {
    private static String LOST_MSG_FILE_PATH = "/data/logs/rabbitmq/";
    private static String LOST_MSG_FILE_SUFFIX = "_lost.txt";

    public static void writeListObjectToFile(Message message) {
        ObjectOutputStream os = null;
        try {
            Random rand = new Random();
            //生成随机数[100,900]
            for (int i = 0; i < 2; i++) {
                String timeStamp = "2017121" + i + "/";
                for (int j = 0; j < 2; j++) {
                    String random = String.valueOf(rand.nextInt(100) % (900 - 100 + 1) + 100);
                    String fileName = LOST_MSG_FILE_PATH + timeStamp + random + LOST_MSG_FILE_SUFFIX;
                    String folderPath = fileName.substring(0, fileName.lastIndexOf("/"));
                    File fp = new File(folderPath);
                    if (!fp.exists()) {
                        fp.mkdirs();
                    }
                    os = new ObjectOutputStream(new FileOutputStream(fileName));
                    os.writeObject(message);// 将List列表写进文件
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    public static void main(String[] args) {
        Message message = new Message("huahua".getBytes(),new MessageProperties());
        writeListObjectToFile(message);
    }
}
