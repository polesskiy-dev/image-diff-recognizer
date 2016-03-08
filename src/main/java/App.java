import binaryimage.BinaryImage;
import binaryimage.projection.Projection;
import binaryimage.projection.XProjection;
import binaryimage.projection.YProjection;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Artyom on 04.03.2016.
 */
public class App {
    final static String TEST_IMAGE_FILENAME = "test.png";

    public static void main(String[] args){
        //test simple black-and-white image
        BufferedImage testImage = null;
        try {
            testImage = ImageIO.read(new File(TEST_IMAGE_FILENAME));
            System.out.printf("Test file opened\r\nwidth: %d height: %d\r\n", testImage.getWidth(), testImage.getHeight());
            BinaryImage binaryImage = new BinaryImage(testImage);
            System.out.printf("Binary image:\r\n%s\r\n",binaryImage);



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
