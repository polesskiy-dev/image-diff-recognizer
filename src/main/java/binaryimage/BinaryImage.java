package binaryimage;

import java.awt.image.BufferedImage;
import java.util.Formatter;

/**
 * Created by Artyom on 04.03.2016.
 */
public class BinaryImage {
    private boolean[][] booleanMatrixImage = null;
    private int width = 0;
    private int height = 0;

    public BinaryImage(BufferedImage image) {
        width = image.getWidth();
        height = image.getHeight();

        //represent images difference as array of boolean
        booleanMatrixImage = new boolean[width][height];
        for (int X = 0; X < width; X++) {
            for (int Y = 0; Y < height; Y++) {
                booleanMatrixImage[X][Y] = ((image.getRGB(X, Y) == 0xFFFFFFFF) ? false : true);
            }
        }
    }

    public BinaryImage(boolean[][] booleanMatrix){
        booleanMatrixImage = booleanMatrix;
        width = booleanMatrix.length;
        height = booleanMatrix[0].length;
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

    public String areaFromScope(Rectangle scope){
        StringBuffer imageStringBuffer = new StringBuffer();

        for (int X = scope.getxLeft(); X < scope.getxRight(); X++) {
            for (int Y = scope.getyLeft(); Y < scope.getyRight(); Y++) {
                imageStringBuffer.append(new Formatter().format("%d ", booleanMatrixImage[X][Y] ? 1 : 0));
            }
            imageStringBuffer.append("\r\n");
        }
        return imageStringBuffer.toString();
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
