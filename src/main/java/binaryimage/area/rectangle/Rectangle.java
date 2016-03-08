package binaryimage.area.rectangle;

import binaryimage.BinaryImage;
import binaryimage.projection.Projection;
import binaryimage.projection.XProjection;
import binaryimage.projection.YProjection;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Artyom on 04.03.2016.
 */
public class Rectangle {
    int xLeft;
    int yLeft;
    int xRight;
    int yRight;

    public Rectangle(int xLeft, int yLeft, int xRight, int yRight) {
        this.xLeft = xLeft;
        this.yLeft = yLeft;
        this.xRight = xRight;
        this.yRight = yRight;
    }

    public int getxLeft() {
        return xLeft;
    }

    public int getyLeft() {
        return yLeft;
    }

    public int getxRight() {
        return xRight;
    }

    public int getyRight() {
        return yRight;
    }

    public int getWidth() {
        return Math.abs(xRight - xLeft);
    }

    public int getHeight() {
        return Math.abs(yRight - yLeft);
    }

    int[] getAsArray() {
        return new int[]{xLeft, yLeft, xRight, yRight};
    }

//    /**
//     * Rectangle scope contains only "false" values
//     *
//     * @param binaryImage
//     * @return
//     */
//    public boolean isEmpty(BinaryImage binaryImage) {
//        Projection xProjection = new XProjection(binaryImage, this);
//        Projection yProjection = new YProjection(binaryImage, this);
//        return xProjection.isAllFalse() && yProjection.isAllFalse();
//    }
//
//    /**
//     * Rectangle projection on any axis contains only "true" values
//     *
//     * @param binaryImage
//     * @return
//     */
//    public boolean isFull(BinaryImage binaryImage) {
//        Projection xProjection = new XProjection(binaryImage, this);
//        Projection yProjection = new YProjection(binaryImage, this);
//        return xProjection.isAllTrue() && yProjection.isAllTrue();
//    }


    @Override
    public String toString() {
        return Arrays.toString(this.getAsArray());
    }
}
