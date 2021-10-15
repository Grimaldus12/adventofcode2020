package day17;

public class Point3D implements Points{

    private final int x,y,z;

    public Point3D(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
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
        return "(" + x + "," + y + "," + z + ")";
    }

    @Override
    public boolean equals(Object compare) {
        if (compare instanceof Point3D) {
            Point3D pointToCompare = (Point3D) compare;
            return (x == pointToCompare.getX() && y == pointToCompare.getY() && z == pointToCompare.getZ());
        }
        return false;
    }
}
