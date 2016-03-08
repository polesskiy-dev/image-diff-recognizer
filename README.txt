This is simple image comparer, showing differences between 2 images.

input images:
original.png
mutated.png

result:
result.png

Core algorithm:
1. Convert differences to binary image (boolean 2D array) and get wrapping Rectangle coordinates
    0 1 0 1 0
    0 1 0 0 1
    0 0 0 0 0
    Rectangle [0,0,4,2]

2. Project binary image to X and Y axis, we will have 2 arrays.
    X projection: 0 1 0 1 0
    Y projection: 1
                  1
                  0

3. Get only "1,1,..,1" sequences from one of projection and store Rectangle (X,Y) coordinates of it.
    Split by X:
    1     1 0
    1 and 0 1
    0     0 0
    wrapping rectangle coordinates: [[1,1,0,2],[3,4,0,2]]

4. Split every rectangle to sub rectangles, until sub rectangles will contain only "full" areas (X,Y axis projection gives "1,1,..,1" sequence)
    Split by Y:
    1      1 0
    1 and  0 1
    result rectangles: [[1,1,0,1],[3,4,0,1]]


