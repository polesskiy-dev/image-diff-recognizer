import binaryimage.BinaryImage;
import binaryimage.area.rectangle.Rectangle;
import binaryimage.area.rectangle.RectangleSplitter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Artyom on 04.03.2016.
 */
public class App {
    final static String ORIGINAL_IMAGE_FILENAME = "original.png";
    final static String MUTATED_IMAGE_FILENAME = "mutated.png";
    final static String RESULT_IMAGE_FILENAME = "result.png";

    public static void main(String[] args) {
        try {
            //lets read images from disk
            BufferedImage originalImage = ImageIO.read(new File(ORIGINAL_IMAGE_FILENAME));
            BufferedImage mutatedImage = ImageIO.read(new File(MUTATED_IMAGE_FILENAME));

            //start timestamp
            long startTimeStamp = System.currentTimeMillis();

            //create binary image whick contains "true" dot if pixels not equals
            BinaryImage binaryImage = new BinaryImage(originalImage, mutatedImage);

            System.out.printf("Binary image created\r\nwidth: %d height: %d\r\n", binaryImage.getWidth(), binaryImage.getHeight());

            /*
            main algorithm
             */
            RectangleSplitter rectangleSplitter = new RectangleSplitter(binaryImage);

            //rectangle over whole image
            Rectangle wholeImageRectangle = new Rectangle(0, 0, binaryImage.getWidth(), binaryImage.getHeight());

            //split rectangle to sub rectangles, which contains only "full" areas (X,Y axis projection gives true,true,..,true sequence)
            ArrayList<Rectangle> subRectangles = rectangleSplitter.getSignificantSubRectangles(wholeImageRectangle);

            //end algorithm timestamp
            long endTimeStamp = System.currentTimeMillis();

            System.out.printf("All done. Algorithm duration %d ms \r\n", endTimeStamp - startTimeStamp);
            System.out.printf("Result rectangles amount: %d, rectangles coordinates:%s \r\n", subRectangles.size(), subRectangles);

            /*
            let's draw on copy of mutated image
             */
            // Create copy
            BufferedImage resultImage = new BufferedImage(mutatedImage.getWidth(), mutatedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = resultImage.createGraphics();
            g2d.drawImage(mutatedImage, 0, 0, null);

            //lets draw rectangles on image
            g2d.setColor(Color.red);
            for (Rectangle rect : subRectangles)
                g2d.drawRect(rect.getxLeft(), rect.getyLeft(), rect.getWidth(), rect.getHeight());

            g2d.dispose();

            //write to disk
            ImageIO.write(resultImage, "png", new File(RESULT_IMAGE_FILENAME));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
