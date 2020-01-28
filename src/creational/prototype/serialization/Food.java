package creational.prototype.serialization;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;

public class Food implements Serializable {

    public int stuff;
    public String whatever;

    public Food(int stuff, String whatever) {
        this.stuff = stuff;
        this.whatever = whatever;
    }

    @Override
    public String toString() {
        return "Food{" +
                "stuff=" + stuff +
                ", whatever='" + whatever + '\'' +
                '}';
    }
}

class Demo {
    public static void main(String[] args) {
        Food food = new Food(42, "orig");
        Food copy = SerializationUtils.roundtrip(food);

        copy.whatever = "copy";

        System.out.println(food);
        System.out.println(copy);
    }
}
