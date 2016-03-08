package binaryimage;

import binaryimage.area.rectangle.Rectangle;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Formatter;

/**
 * Created by Artyom on 04.03.2016.
 * Binary image
 */
public class BinaryImage {
    private final static int DIFFERENCE = (int) (0.05 * 0xFF);
    private boolean[][] booleanMatrixImage = null;
    private int width = 0;
    private int height = 0;

//    public BinaryImage(BufferedImage image) {
//        width = image.getWidth();
//        height = image.getHeight();
//
//        //represent images difference as array of boolean
//        booleanMatrixImage = new boolean[width][height];
//        for (int X = 0; X < width; X++) {
//            for (int Y = 0; Y < height; Y++) {
//                booleanMatrixImage[X][Y] = ((image.getRGB(X, Y) == 0xFFFFFFFF) ? false : true);
//            }
//        }
//    }

    public BinaryImage(boolean[][] booleanMatrix) {
        booleanMatrixImage = booleanMatrix;
        width = booleanMatrix.length;
        height = booleanMatrix[0].length;
    }

    /**
     * Constructor from 2 images
     *
     * @param originalImage
     * @param mutatedImage
     * @throws Exception
     */
    public BinaryImage(BufferedImage originalImage, BufferedImage mutatedImage) throws Exception {

        width = originalImage.getWidth();
        height = originalImage.getHeight();
        booleanMatrixImage = new boolean[width][height];

        //lets compare pixel-by-pixel
        for (int X = 0; X < width; X++) {
            for (int Y = 0; Y < height; Y++) {
                Color originalImageColor = new Color(originalImage.getRGB(X, Y));
                Color mutatedImageColor = new Color(mutatedImage.getRGB(X, Y));

                //if difference between images colors on any channel > DIFFERENCE, add point to binary image
                if (Math.abs(originalImageColor.getRed() - mutatedImageColor.getRed()) > DIFFERENCE ||
                        Math.abs(originalImageColor.getGreen() - mutatedImageColor.getGreen()) > DIFFERENCE ||
                        Math.abs(originalImageColor.getBlue() - mutatedImageColor.getBlue()) > DIFFERENCE) {
                        booleanMatrixImage[X][Y] = true;
                }
            }
        }
    }


    public boolean[][] getBooleanMatrixImage() {
        return booleanMatrixImage;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }



    @Override
    public String toString() {
        StringBuffer imageStringBuffer = new StringBuffer();

        for (int X = 0; X < width; X++) {
            for (int Y = 0; Y < height; Y++) {
                imageStringBuffer.append(new Formatter().format("%d ", booleanMatrixImage[X][Y] ? 1 : 0));
            }
            imageStringBuffer.append("\r\n");
        }
        return imageStringBuffer.toString();
    }
}
