package creational.singletone;

public class InnerStaticSingleton {

    private InnerStaticSingleton(){
        System.out.println("init");
    }

    private static class Impl {
        private static final InnerStaticSingleton INSTANCE = new InnerStaticSingleton();
    }

    public static InnerStaticSingleton getInstance() {
        return Impl.INSTANCE;
    }
}

class DemoStatic {
    public static void main(String[] args) {
        InnerStaticSingleton.getInstance();
        InnerStaticSingleton.getInstance();
    }
}
