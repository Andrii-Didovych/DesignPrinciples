package creational.singletone;

import java.util.HashMap;

enum Subsystem {
    PRIMARY,
    AUXILIARY,
    FALLBACK
}

class Printer {
    private static int instanceCount = 0;

    private Printer() {
        instanceCount++;
        System.out.println(String.format("A total of %s instances created so far", instanceCount));
    }

    private static HashMap<Subsystem, Printer> instances = new HashMap<>();

    public static Printer get(Subsystem subsystem) {
        if (instances.containsKey(subsystem)) {
            return instances.get(subsystem);
        }

        Printer instance = new Printer();
        instances.put(subsystem, instance);
        return instance;
    }
}

public class Multiton {
    public static void main(String[] args) {
        Printer.get(Subsystem.AUXILIARY);
        Printer.get(Subsystem.FALLBACK);
        Printer.get(Subsystem.PRIMARY);
        Printer.get(Subsystem.PRIMARY);
        Printer.get(Subsystem.PRIMARY);
        Printer.get(Subsystem.PRIMARY);
    }
}
