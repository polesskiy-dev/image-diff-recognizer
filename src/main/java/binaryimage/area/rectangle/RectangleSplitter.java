package binaryimage.area.rectangle;

import binaryimage.BinaryImage;
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

    /**
     * Split to rectangles, which contains "full" areas (X,Y axis projection gives true,true,..,true sequence)
     *
     * @return
     */
    public ArrayList<Rectangle> getSignificantSubRectangles(Rectangle rectangleToSplit) {
        RectangleSplitter rectangleSplitter = new RectangleSplitter(binaryImage);

        //result rectangles, which contains "full" areas (X,Y axis projection gives true,true,..,true sequence)
        ArrayList<Rectangle> resultRectangles = new ArrayList<>();
        resultRectangles.add(rectangleToSplit);

        /*
        Replace every splittable Rectangle by their splitting result Rectangles
        dirty hack - remember that cycle body will be invoked before 2nd "for" condition checking
         */
        for (int i = 0; i < resultRectangles.size(); ) {
            ArrayList<Rectangle> subRectangles = rectangleSplitter.splitToSub(resultRectangles.get(i));
            //System.out.printf("splitToSub from %d rectangles, number %d, to:%s\r\n",resultRectangles.size(),i,subRectangles);
            if (subRectangles != null) {
                resultRectangles.remove(i);
                resultRectangles.addAll(i, subRectangles);
            } else i++;
        }

        return resultRectangles;
    }

    /**
     * Split to rectangles which contains "true" dots
     * first trying to split by X axis, if can't - trying by Y
     *
     * @param bigRectangle
     * @return
     */
    private ArrayList<Rectangle> splitToSub(Rectangle bigRectangle) {
        ArrayList<Rectangle> subRectangles;
        Projection xProjection = new XProjection(binaryImage, bigRectangle);
        Projection yProjection = new YProjection(binaryImage, bigRectangle);

        //try to splitToSub by X axis if projection could be splitToSub
        if (xProjection.isAllFalse() == false && xProjection.isAllTrue() == false) {
            //debug
//            System.out.println("Splitting by X axis");

            subRectangles = this.splitByX(bigRectangle);
        } else
            //try to splitToSub by Y axis if projection could be splitToSub
            if (yProjection.isAllFalse() == false && yProjection.isAllTrue() == false) {
                //debug
//                System.out.println("Splitting by Y axis");

                subRectangles = this.splitByY(bigRectangle);
            } else return null;

        return subRectangles;
    }

    /**
     * Split to sub rectangles by X axis
     *
     * @param bigRectangle
     * @return
     */
    private ArrayList<Rectangle> splitByX(Rectangle bigRectangle) {
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
//        for (Rectangle rectangle : subRectangles) {
//            System.out.printf("X splitToSub rectangle: %s, empty: %b, full: %b\n", rectangle, rectangle.isEmpty(binaryImage), rectangle.isFull(binaryImage));
//            System.out.printf("%s\r\n", binaryImage.areaFromScope(rectangle));
//        }

        return subRectangles;
    }

    /**
     * Split to sub rectangles by X axis
     *
     * @param bigRectangle
     * @return
     */
    private ArrayList<Rectangle> splitByY(Rectangle bigRectangle) {
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
//        for (Rectangle rectangle : subRectangles) {
//            System.out.printf("Y splitToSub rectangle: %s, empty: %b, full: %b\n", rectangle, rectangle.isEmpty(binaryImage), rectangle.isFull(binaryImage));
//            System.out.printf("%s\r\n", binaryImage.areaFromScope(rectangle));
//        }

        return subRectangles;
    }
}
