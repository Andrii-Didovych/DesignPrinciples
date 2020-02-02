package creational.singletone;

import java.io.File;
import java.io.IOException;

public class StaticBlockSingleton {

    private StaticBlockSingleton() throws IOException {
        System.out.println("Singleton will be initialized");
        File.createTempFile(".", ".");
    }

    private static StaticBlockSingleton instance;

    static {
        try {
            instance = new StaticBlockSingleton();
        } catch (Exception e) {
            System.err.println("failed");
        }
    }

    public static StaticBlockSingleton getInstance() {
        return instance;
    }
}

class Demo {
    public static void main(String[] args) {
        StaticBlockSingleton.getInstance();
        StaticBlockSingleton.getInstance();
    }}
