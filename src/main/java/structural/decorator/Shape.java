package structural.decorator;

import java.util.function.Supplier;

interface Shape {
    String info();
}

class Circle implements Shape {

    private float radius;

    public Circle() {}

    public Circle(float radius) {
        this.radius = radius;
    }

    Circle resize(float factor) {
        radius *= factor;
        return this;
    }

    @Override
    public String info() {
        return "A circle of radius " + radius;
    }
}

class Square implements Shape {


    private float side;

    public Square() {}

    public Square(float side) {
        this.side = side;
    }

    @Override
    public String info() {
        return "A Square of side " + side;
    }
}

class ColorShape<T extends Shape> implements Shape {

    private Shape shape;
    private String color;

    public ColorShape(Supplier<? extends T> ctor, String color) {
        shape = ctor.get();
        this.color = color;
    }

    @Override
    public String info() {
        return shape.info() + " has color " + color;
    }
}

class TransparentShape<T extends Shape> implements Shape {

    private Shape shape;
    private int transparency;

    public TransparentShape(Supplier<? extends T> ctor, int transparency) {
        shape = ctor.get();
        this.transparency = transparency;
    }

    @Override
    public String info() {
        return shape.info() + " has transparency " + transparency;
    }
}

class StaticDemo {

    public static void main(String[] args) {
        final ColorShape<Square> squareColorShape = new ColorShape<>(() -> new Square(20), "blue");
        System.out.println(squareColorShape.info());

        final TransparentShape<ColorShape> green =
                new TransparentShape<>(() -> new ColorShape(() -> new Circle(5), "green"), 50);
        System.out.println(green.info());
    }
}

//class ColorShape implements Shape {
//
//    private Shape shape;
//    private String color;
//
//    public ColorShape(Shape shape, String color) {
//        this.shape = shape;
//        this.color = color;
//    }
//
//    @Override
//    public String info() {
//        return shape.info() + " has the color " + color;
//    }
//}

//class TransparentShape implements Shape {
//
//    private Shape shape;
//    private int transparency;
//
//    public TransparentShape(Shape shape, int transparency) {
//        this.shape = shape;
//        this.transparency = transparency;
//    }
//
//    @Override
//    public String info() {
//        return shape.info() + " has the transparency " + transparency;
//    }
//}
//
//class DemoShape {
//
//    public static void main(String[] args) {
////        final Circle circle = new Circle(10);
////        System.out.println(circle.info());
////
////        final ColorShape blue = new ColorShape(new Square(12), "blue");
////        System.out.println(blue.info());
////
////        final TransparentShape transparentShape = new TransparentShape(new ColorShape(new Circle(5).resize(2), "red"),1);
////
////        System.out.println(transparentShape.info());
//
//    }
//
//}
