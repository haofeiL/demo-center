
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @description:
 * @author: haofeiL
 * @createDate: 2018/9/26 14:32
 * @version: v1.0
 */
public class Pdf2Image {
    public static void main(String[] args) {
        String filePath = "E:\\xunlong\\项目\\图片识别\\example\\20010134.NSK.pdf";
        List<String> imageList = pdfToImagePath(filePath);
    }


    public static List<String> pdfToImagePath(String filePath) {
        List<String> list = new ArrayList<String>();
        String fileDirectory = filePath.substring(0, filePath.lastIndexOf("."));//获取去除后缀的文件路径
        String imagePath;
        File file = new File(filePath);
        try {
            File f = new File(fileDirectory);
            if (!f.exists()) {
                f.mkdir();
            }
            PDDocument doc = PDDocument.load(file);
            PDFRenderer renderer = new PDFRenderer(doc);
            int pageCount = doc.getNumberOfPages();
            for (int i = 0; i < pageCount; i++) {
                // 方式1,第二个参数是设置缩放比(即像素)
                BufferedImage image = renderer.renderImageWithDPI(i, 296);
                // 方式2,第二个参数是设置缩放比(即像素)
//                BufferedImage image = renderer.renderImage(i, 1.25f);  //第二个参数越大生成图片分辨率越高，转换时间也就越长
                imagePath = fileDirectory + "/" + i + ".jpg";
                ImageIO.write(image, "PNG", new File(imagePath));
                list.add(imagePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }



    /*public void toImage() {
        try {
            PDDocument doc = PDDocument.load(new File("F:\\java56班\\eclipse-SDK-4.2-win32\\pdfwithText.pdf"));
            int pageCount = doc.getNumberOfPages();
            System.out.println(pageCount);
            PDPageTree pages = doc.getDocumentCatalog().getPages();
            Iterator iterator = pages.iterator();
            while (iterator.hasNext()) {
                PDPage page = (PDPage) iterator.next();
//                PDPage page = (PDPage) pages.get(i);
                BufferedImage image = page..convertToImage();
                Iterator iter = ImageIO.getImageWritersBySuffix("jpg");
                ImageWriter writer = (ImageWriter) iter.next();
                File outFile = new File("F:\\java56班\\eclipse-SDK-4.2-win32\\"+ i + ".jpg");
                FileOutputStream out = new FileOutputStream(outFile);
                ImageOutputStream outImage = ImageIO
                        .createImageOutputStream(out);
                writer.setOutput(outImage);
                writer.write(new IIOImage(image, null, null));
            }
            doc.close();
            System.out.println("over");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }*/

}
