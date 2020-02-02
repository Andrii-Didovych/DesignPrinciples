package creational.singletone;

public class LazySingleton {

    private static LazySingleton instance;

    private LazySingleton() {
        System.out.println("init a lazy singleton");
    }

//    public static synchronized LazySingleton getInstance() {
//        if (instance == null) {
//            instance = new LazySingleton();
//        }
//        return instance;
//    }

    //double check locking
    public static  LazySingleton getInstance() {
        if (instance == null) {
            synchronized (LazySingleton.class) {
                if (instance == null) {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }
}

class LazyDemo {
    public static void main(String[] args) {
        LazySingleton.getInstance();
        LazySingleton.getInstance();

    }
}