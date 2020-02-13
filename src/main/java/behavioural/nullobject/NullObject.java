package behavioural.nullobject;


import java.lang.reflect.Proxy;

interface Log {
    void info(String msg);
    void warn(String msg);
}

class ConsoleLog implements Log {

    @Override
    public void info(String msg) {
        System.out.println(msg);
    }

    @Override
    public void warn(String msg) {
        System.out.println("WARNING: " + msg);
    }
}

class BankAccount {
    private Log log;
    private int balance;

    public BankAccount(Log log) {
        this.log = log;
    }

    public void deposit(int amount) {
        balance += amount;

        log.info("Deposited " + amount
                + ", balance is now " + balance);
    }

}

final class NullLog implements Log {

    @Override
    public void info(String msg) {}

    @Override
    public void warn(String msg) {}
}

class NullObjectDemo {

    public static <T> T noOp(Class<T> itf) {
        return (T) Proxy.newProxyInstance(
                itf.getClassLoader(),
                new Class<?>[]{itf},
                (o, method, objects) -> {
                    if (method.getReturnType().equals(Void.TYPE)) {
                        return null;
                    } else {
                        return method.getReturnType().getConstructor().newInstance();
                    }
                }
        );
    }

    public static void main(String[] args) {
        //Log log = new ConsoleLog();
        //Log log = null;

//        Log log = new NullLog();

        Log log = noOp(Log.class);

        BankAccount ba = new BankAccount(log);
        ba.deposit(100);
    }
}