package binaryimage.projection;

import binaryimage.BinaryImage;
import binaryimage.Rectangle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Artyom on 04.03.2016.
 */
public abstract class Projection {
    private BinaryImage binaryImage;
    private boolean[] projection;

    Projection(BinaryImage binaryImage) {
        this.binaryImage = binaryImage;
    }

    /**
     * Get coordinates of all 'true,true..true' sequences
     * from start to end+1 of sequence
     *
     * @return
     */
    public ArrayList<int[]> findTrueSequences() {
        ArrayList<int[]> result = new ArrayList<>();

        //Boundary values
        if (this.isAllFalse()) return result;//empty list
        if (this.isAllTrue()) {
            result.add(new int[]{0, projection.length});
            return result;
        }

        int startIndex = 0;
        int endIndex = 0;

        //iterate array and find true sequences coordinates
        for (int i = 1; i < projection.length; i++) {
            if ((projection[i - 1] == false) && (projection[i] == true)) startIndex = i;
            if ((projection[i - 1] == true) && (projection[i] == false)) {
                endIndex = i;
                result.add(new int[]{startIndex,endIndex});
            }
            //handle last element
            if ((i == projection.length - 1) && (projection[i] == true)) {
                result.add(new int[]{startIndex,projection.length});
            }
        }

        return result;
    }

    /**
     * Checks that all elements of projection is true
     *
     * @return
     */
    public boolean isAllTrue() {
        for (boolean value : this.projection) if (value == false) return false;
        return true;
    }

    /**
     * Checks that all elements of projection is false
     *
     * @return
     */
    public boolean isAllFalse() {
        for (boolean value : this.projection) if (value == true) return false;
        return true;
    }

    public void setProjection(boolean[] projection) {
        this.projection = projection;
    }

    public boolean[] getProjection() {
        return projection;
    }
}
