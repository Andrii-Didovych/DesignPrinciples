package solid.lsp;

/*
    якщо S підтип T, тоді об'єкти типу T в програмі можуть бути заміщені об'єктами
    типу S без будь-яких змін бажаних властивостей цієї програми
 */


public class Rectangle {

    protected int width, height;

    public Rectangle() {
    }

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getArea() {
        return width * height;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }

    public boolean isSquare() {
        return height == width;
    }
}

class Square extends Rectangle {

    public Square() {
    }

    public Square(int size) {
        width = height = size;
    }

    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        super.setHeight(width);
    }

    @Override
    public void setHeight(int height) {
        super.setWidth(height);
        super.setHeight(height);
    }
}

class RectangleFactory {

    public static Rectangle newRectangle(int height, int width) {
        return new Rectangle(width, height);
    }

    public static Rectangle newSquare(int side) {
        return new Rectangle(side, side);
    }

}

class Demo {

    static void useIt(Rectangle rectangle) {
        int width = rectangle.getWidth();
        rectangle.setHeight(10);
        //area = width *10
        System.out.println(
                "Expected area of " + (width * 10) + ", got " + rectangle.getArea()
        );
    }

    public static void main(String[] args) {
        Rectangle rc = new Rectangle(2, 3);
        useIt(rc);

        Rectangle square = new Square(5);
        useIt(square);

        Rectangle rectangle = RectangleFactory.newSquare(5);
        useIt(rectangle);
    }
}
