package binaryimage.projection;

import binaryimage.BinaryImage;
import binaryimage.area.rectangle.Rectangle;

/**
 * Created by Artyom on 04.03.2016.
 */
public class XProjection extends Projection {
    public XProjection(BinaryImage binaryImage, Rectangle scope) {
        super(binaryImage);

        //project to X axis, if "true" occurs in column, projection result become true;
        boolean[] xProjection = new boolean[scope.getxRight()-scope.getxLeft()];

        for (int X = scope.getxLeft(); X< scope.getxRight(); X++){
            for (int Y = scope.getyLeft(); Y<scope.getyRight(); Y++){
                xProjection[X-scope.getxLeft()] |= binaryImage.getBooleanMatrixImage()[X][Y];
            }
        }

        super.setProjection(xProjection);
    }
}
