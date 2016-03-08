import binaryimage.BinaryImage;
import binaryimage.RectangleSplitter;
import binaryimage.Rectangle;
import binaryimage.projection.Projection;
import binaryimage.projection.XProjection;
import binaryimage.projection.YProjection;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Artyom on 04.03.2016.
 */
public class TestProjection {
    //    @Test
    public void testSequenceFind() {
        boolean[][] testMatrix = new boolean[][]{
                {false, true, false, true},
                {false, false, false, false},
                {true, true, true, true},
                {false, true, false, false}
        };
        //test binaryImage and vision scope
        BinaryImage binaryImage = new BinaryImage(testMatrix);
        Rectangle scope = new Rectangle(0, 0, binaryImage.getWidth(), binaryImage.getHeight());
        System.out.printf("Binary image :\r\n%s\r\n", binaryImage);
        System.out.printf("Scope:\r\n%s\r\n", binaryImage.areaFromScope(scope));

        //project to axis
        Projection xProjection = new XProjection(binaryImage, scope);
        System.out.printf("X projection:%s, allTrue: %b, allFalse: %b\r\n", Arrays.toString(xProjection.getProjection()), xProjection.isAllTrue(), xProjection.isAllFalse());

        Projection yProjection = new YProjection(binaryImage, scope);
        System.out.printf("Y projection:%s, allTrue: %b, allFalse: %b\r\n", Arrays.toString(yProjection.getProjection()), yProjection.isAllTrue(), yProjection.isAllFalse());

        //get sequences from projections
        System.out.println("\r\nX projection sequences:");
        for (int[] sequenceCoordinates : xProjection.findTrueSequences()) {
            System.out.printf("%s ", Arrays.toString(sequenceCoordinates));
        }

        System.out.println("\r\nY projection sequences:");
        for (int[] sequenceCoordinates : yProjection.findTrueSequences()) {
            System.out.printf("%s ", Arrays.toString(sequenceCoordinates));
        }
    }

    //    @Test
    public void testXsplitting() {
        boolean[][] testMatrix = new boolean[][]{
                {false, true, false, true},
                {false, false, false, false},
                {true, true, true, true},
                {false, true, false, false}
        };
        BinaryImage binaryImage = new BinaryImage(testMatrix);
        Rectangle scope = new Rectangle(0, 0, binaryImage.getWidth(), binaryImage.getHeight());
        System.out.printf("Binary image :\r\n%s\r\n", binaryImage);
        System.out.printf("Scope: %s\r\n%s\r\n", scope, binaryImage.areaFromScope(scope));

        RectangleSplitter rectangleSplitter = new RectangleSplitter(binaryImage);

        //get list of rectangles by splitting big rectangle scope by X axis
        ArrayList<Rectangle> xSplittedRectangles = rectangleSplitter.splitByX(scope);
    }

    //    @Test
    public void testYsplitting() {
        boolean[][] testMatrix = new boolean[][]{
                {false, true, false, true},
                {false, false, false, false},
                {true, true, false, true},
                {false, true, false, false}
        };
        BinaryImage binaryImage = new BinaryImage(testMatrix);
        Rectangle scope = new Rectangle(0, 0, binaryImage.getWidth(), binaryImage.getHeight());
        System.out.printf("Binary image :\r\n%s\r\n", binaryImage);
        System.out.printf("Scope: %s\r\n%s\r\n", scope, binaryImage.areaFromScope(scope));

        RectangleSplitter rectangleSplitter = new RectangleSplitter(binaryImage);

        //get list of rectangles by splitting big rectangle scope by X axis
        ArrayList<Rectangle> ySplittedRectangles = rectangleSplitter.splitByY(scope);
    }

    @Test
    public void testRecursiveSplitting() {
        boolean[][] testMatrix = new boolean[][]{
                {false, true, false, true},
                {false, false, false, false},
                {true, true, false, true},
                {false, true, false, false}
//                {false, false, true, false}
        };
        BinaryImage binaryImage = new BinaryImage(testMatrix);
        Rectangle scope = new Rectangle(0, 0, binaryImage.getWidth(), binaryImage.getHeight());

        //debug
        System.out.printf("Binary image :\r\n%s\r\n", binaryImage);
        System.out.printf("Scope: %s\r\n%s\r\n", scope, binaryImage.areaFromScope(scope));

//        RectangleSplitter rectangleSplitter = new RectangleSplitter(binaryImage);
        ArrayList<Rectangle> subRectangles = splitRect(scope, binaryImage);

//        ArrayList<Rectangle> subRectangles = rectangleSplitter.split(scope);
//        if (subRectangles != null) {
//            for (Rectangle subRect : subRectangles) {
//                rectangleSplitter.split(subRect);
//            }
//        }
        System.out.printf("Result rectangles:%s",subRectangles);
    }

//    private ArrayList<Rectangle> splitEvery(ArrayList<Rectangle> bigRectangles, BinaryImage binaryImage) {
//        RectangleSplitter rectangleSplitter = new RectangleSplitter(binaryImage);
//        ArrayList<Rectangle> subRectangles = new ArrayList<>();
//
//        if (bigRectangles != null) {
//            for (Rectangle bigRectangle : bigRectangles) {
//                subRectangles = splitEvery(rectangleSplitter.split(bigRectangle), binaryImage);
//            }
//        } else return null;
//
//        return subRectangles;
//    }

    private ArrayList<Rectangle> splitRect(Rectangle bigRectangle, BinaryImage binaryImage) {
        RectangleSplitter rectangleSplitter = new RectangleSplitter(binaryImage);
        ArrayList<Rectangle> subRectangles;// = new ArrayList<>();

//        if (bigRectangle!=null){
        subRectangles = rectangleSplitter.split(bigRectangle);
        if (subRectangles != null) {
            for (Rectangle rectangle : subRectangles) {
                splitRect(rectangle, binaryImage);
            }
        } else return null;

        return subRectangles;
    }



}
