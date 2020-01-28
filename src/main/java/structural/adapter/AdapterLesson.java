package structural.adapter;

public class AdapterLesson {

    public static void main(String[] args) {
        CarWash carWash = new CarWash();
        carWash.washCar(new Audi());
        carWash.washCar(new TrackWrap(new Man()));
    }
}

class TrackWrap implements Car {

    private final Track track;

    TrackWrap(Track track) {
        this.track = track;
    }

    @Override
    public void wash() {
        track.wash();
    }
}

interface Track {
    void wash();
}

class Man implements Track {

    @Override
    public void wash() {
        System.out.println(String.format("Wash the %s", this.getClass().getSimpleName()));
    }
}

interface Car {
    void wash();
}

class Audi implements Car {
    @Override
    public void wash() {
        System.out.println(String.format("Wash the %s", this.getClass().getSimpleName()));
    }
}

class CarWash {

    void washCar(Car car) {
        car.wash();
    }
}


