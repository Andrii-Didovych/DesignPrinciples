package structural.bridge;

import java.util.Arrays;

public class ProgramCreator {

    public static void main(String[] args) {
        Program[] programs = { new BankSystem(new JavaDeveloper()), new StockExchange(new CppDeveloper())};

        Arrays.stream(programs).forEachOrdered(Program::developProgram);
    }
}

abstract class Program {
    protected Developer developer;

    protected Program(Developer developer) {
        this.developer = developer;
    }

    public abstract void developProgram();
}

class BankSystem extends Program {

    protected BankSystem(Developer developer) {
        super(developer);
    }

    @Override
    public void developProgram() {
        System.out.println("Bank system development in progress");
        developer.writeCode();
    }
}

class StockExchange extends Program {

    protected StockExchange(Developer developer) {
        super(developer);
    }

    @Override
    public void developProgram() {
        System.out.println("Stock exchange development in progress");
        developer.writeCode();
    }
}


interface Developer {
    void writeCode();

}
class JavaDeveloper implements Developer {

    @Override
    public void writeCode() {
        System.out.println("Dev writes java code");
    }

}
class CppDeveloper implements Developer {

    @Override
    public void writeCode() {
        System.out.println("Dev writes c++ code");
    }

}
