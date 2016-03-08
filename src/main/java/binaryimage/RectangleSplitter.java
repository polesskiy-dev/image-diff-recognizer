package binaryimage;

import binaryimage.projection.Projection;
import binaryimage.projection.XProjection;
import binaryimage.projection.YProjection;

import java.util.ArrayList;

/**
 * Created by Artyom on 04.03.2016.
 */
public class RectangleSplitter {
    private BinaryImage binaryImage;

    public RectangleSplitter(BinaryImage binaryImage) {
        this.binaryImage = binaryImage;
    }

    public ArrayList<Rectangle> split(Rectangle bigRectangle) {
        ArrayList<Rectangle> subRectangles = new ArrayList<>();
        Projection xProjection = new XProjection(binaryImage, bigRectangle);
        Projection yProjection = new YProjection(binaryImage, bigRectangle);

        //try to split by X axis if projection could be split
        if (xProjection.isAllFalse() == false && xProjection.isAllTrue() == false) {
            //debug
            System.out.println("Splitting by X axis");

            subRectangles = this.splitByX(bigRectangle);
        } else
            //try to split by Y axis if projection could be split
            if (yProjection.isAllFalse() == false && yProjection.isAllTrue() == false) {
                //debug
                System.out.println("Splitting by Y axis");

                subRectangles = this.splitByY(bigRectangle);
            } else return null;

        return subRectangles;
    }

    public ArrayList<Rectangle> splitByX(Rectangle bigRectangle) {
        ArrayList<Rectangle> subRectangles = new ArrayList<>();

        //project big rectangle to X axis
        Projection xProjection = new XProjection(binaryImage, bigRectangle);

        /*
        * Split big rectangle to sub rectangles by finding "true" ("true,true..true") sequences in projection
        * create rectangle from X coordinates of "true" sequences and Y coordinates of big rectangle
        */
        for (int[] subRectangleXcoordinates : xProjection.findTrueSequences()) {
            int xLeft = subRectangleXcoordinates[0] + bigRectangle.getxLeft();
            int yLeft = bigRectangle.getyLeft();
            int xRight = subRectangleXcoordinates[1] + bigRectangle.getxLeft();
            int yRight = bigRectangle.getyRight();
            subRectangles.add(new Rectangle(xLeft, yLeft, xRight, yRight));
        }

        //debug
        for (Rectangle rectangle : subRectangles) {
            System.out.printf("X split rectangle: %s, empty: %b, full: %b\n", rectangle, rectangle.isEmpty(binaryImage), rectangle.isFull(binaryImage));
            System.out.printf("%s\r\n", binaryImage.areaFromScope(rectangle));
        }

        return subRectangles;
    }

    public ArrayList<Rectangle> splitByY(Rectangle bigRectangle) {
        ArrayList<Rectangle> subRectangles = new ArrayList<>();

        //project big rectangle to Y axis
        Projection yProjection = new YProjection(binaryImage, bigRectangle);

        /*
        * Split big rectangle to sub rectangles by finding "true" ("true,true..true") sequences in projection
        * create rectangle from Y coordinates of "true" sequences and X coordinates of big rectangle
        */
        for (int[] subRectangleYcoordinates : yProjection.findTrueSequences()) {
            int xLeft = bigRectangle.getxLeft();
            int yLeft = subRectangleYcoordinates[0] + bigRectangle.getyLeft();
            int xRight = bigRectangle.getxRight();
            int yRight = subRectangleYcoordinates[1] + bigRectangle.getyLeft();
            subRectangles.add(new Rectangle(xLeft, yLeft, xRight, yRight));
        }

        //debug
        for (Rectangle rectangle : subRectangles) {
            System.out.printf("Y split rectangle: %s, empty: %b, full: %b\n", rectangle, rectangle.isEmpty(binaryImage), rectangle.isFull(binaryImage));
            System.out.printf("%s\r\n", binaryImage.areaFromScope(rectangle));
        }

        return subRectangles;
    }


}
