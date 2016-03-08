package binaryimage.projection;

import binaryimage.BinaryImage;
import binaryimage.area.rectangle.Rectangle;

/**
 * Created by Artyom on 04.03.2016.
 */
public class YProjection extends Projection {
    public YProjection(BinaryImage binaryImage, Rectangle scope) {
        super(binaryImage);

        //project to Y axis, if "true" occurs in column, projection result become true;
        boolean[] yProjection = new boolean[scope.getyRight()-scope.getyLeft()];

        for (int Y = scope.getyLeft(); Y< scope.getyRight(); Y++){
            for (int X = scope.getxLeft(); X<scope.getxRight(); X++){
                yProjection[Y-scope.getyLeft()] |= binaryImage.getBooleanMatrixImage()[X][Y];
            }
        }

        super.setProjection(yProjection);
    }
}
