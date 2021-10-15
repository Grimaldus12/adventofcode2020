package day17;

public class Point4D implements Points{

    private final int x,y,z,w;

    public Point4D(int x, int y, int z, int w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }


    @Override
    public String toString() {
        return "(" + x + "," + y + "," + z + "," + w + ")";
    }

    @Override
    public boolean equals(Object compare) {
        if (compare instanceof Point4D) {
            Point4D pointToCompare = (Point4D) compare;
            return (x == pointToCompare.getX() && y == pointToCompare.getY() && z == pointToCompare.getZ() && w == pointToCompare.getW());
        }
        return false;
    }

    public int getW() {
        return w;
    }
}

