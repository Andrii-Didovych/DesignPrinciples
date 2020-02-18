package refactoring;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class IfStatment {

    int result = Integer.MIN_VALUE;

    public int calculate(int a, int b, String operator) {
        if ("add".equals(operator)) {
            result = a + b;
        } else if ("multiply".equals(operator)) {
            result = a * b;
        } else if ("divide".equals(operator)) {
            result = a / b;
        } else if ("subtract".equals(operator)) {
            result = a - b;
        }
        return result;
    }

    public int calculateUsingSwitch(int a, int b, String operator) {
        switch (operator) {
            case "add":
                result = a + b;
                break;
            // other cases
        }
        return result;
    }
}
//map
interface Operation {
    int apply(int a, int b);
}

class Addition implements Operation {
    @Override
    public int apply(int a, int b) {
        return a + b;
    }
}

class Division implements Operation {
    @Override
    public int apply(int a, int b) {
        return a / b;
    }
}

class OperatorFactory {
    static Map<String, Operation> operationMap = new HashMap<>();
    static {
        operationMap.put("add", new Addition());
        operationMap.put("divide", new Division());
        // more operators
    }

    private static Optional<Operation> getOperation(String operator) {
        return Optional.ofNullable(operationMap.get(operator));
    }

    public int calculateUsingFactory(int a, int b, String operator) {
        Operation targetOperation = OperatorFactory
                .getOperation(operator)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Operator"));
        return targetOperation.apply(a, b);
    }
}

class Demo {
    public static void main(String[] args) {
        System.out.println(new OperatorFactory().calculateUsingFactory(1, 4, "add"));

        System.out.println(new Calculator().calculate(new AddCommand(1, 4)));
    }
}

//command
interface Command {
    Integer execute();
}

class AddCommand implements Command {
    int a, b;

    public AddCommand(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public Integer execute() {
        return a + b;
    }
}

class Calculator {
    public int calculate(Command command) {
        return command.execute();
    }
}


