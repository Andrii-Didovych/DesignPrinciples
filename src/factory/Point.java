package factory;

enum CoordinateSystem {
    CARTESIAN,
    POLAR
}

class PointWithUglyConstructor {

    private double x, y;

    public PointWithUglyConstructor(double x, double y, CoordinateSystem system) {
        switch (system) {
            case CARTESIAN:
                this.x = x;
                this.y = y;
                break;
            case POLAR:
                this.x = x * Math.cos(y);
                this.y = x * Math.sin(y);
                break;
        }
    }
}

public class Point {

    private double x, y;

    private Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static class Factory {
        public static Point newCartesianPoint(double x, double y) {
            return new Point(x, y);
        }

        public static Point newPolarPoint(double rho, double theta) {
            return new Point(rho * Math.cos(theta), rho * Math.sin(theta));
        }
    }
}

class Demo {

    public static void main(String[] args) {

        //bad practice
        new PointWithUglyConstructor(2, 3, CoordinateSystem.POLAR);
        new PointWithUglyConstructor(2, 3, CoordinateSystem.CARTESIAN);

        //good practice
        Point.Factory.newPolarPoint(2, 3);
        Point.Factory.newCartesianPoint(2, 3);
    }
}