package factory;

import org.apache.commons.lang3.tuple.Pair;
import org.reflections.Reflections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

interface HotDrink {
    void consume();
}

class Tea implements HotDrink {

    @Override
    public void consume() {
        System.out.println("This tea is delicious");
    }
}

class Coffee implements HotDrink {

    @Override
    public void consume() {
        System.out.println("This coffee is delicious");
    }
}

interface HotDrinkFactory {
    HotDrink prepare(int amount);
}

class TeaFactory implements HotDrinkFactory {

    @Override
    public HotDrink prepare(int amount) {
        System.out.println(
                String.format("Put in tea bag, boil water, pour %sml, add lemon, enjoy!", amount));
        return new Tea();
    }
}

class CoffeeFactory implements HotDrinkFactory {

    @Override
    public HotDrink prepare(int amount) {
        System.out.println(
                String.format("Grain some beans, boil some water, pou %sml, add cream and sugar, enjoy!", amount));
        return new Coffee();
    }
}

enum Factories {

    TEA_FACTORY("Tea", new TeaFactory()),
    COFFEE_FACTORY("Coffee", new CoffeeFactory());

    private String name;
    private HotDrinkFactory factory;

    Factories(String name, HotDrinkFactory factory) {
        this.name = name;
        this.factory = factory;
    }

    public String getName() {
        return name;
    }

    public HotDrinkFactory getFactory() {
        return factory;
    }
}

class HotDrinkMachine {

    private static List<Pair<String, HotDrinkFactory>> namedFactories = new ArrayList<>();

    private HotDrinkMachine() {}

    public HotDrink makeDrink() throws IOException {
        System.out.println("Available drinks:");
        for (int index = 0; index < namedFactories.size(); index++) {
            Pair<String, HotDrinkFactory> item = namedFactories.get(index);
            System.out.println("" + index + ": " + item.getKey());
        }

        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String s;
            int i, amount;
            if ((s = bufferedReader.readLine()) != null && (i = Integer.parseInt(s)) >= 0 && i< namedFactories.size()) {
                System.out.println("Specify amount:");
                s = bufferedReader.readLine();
                if (s != null && (amount = Integer.parseInt(s)) > 0) {
                    final HotDrink hotDrink = namedFactories.get(i).getValue().prepare(amount);
                    namedFactories.clear();
                    return hotDrink;
                }
            }
            System.out.println("Incorrect input, try again.");
        }
    }

    public static class InitFactory {

        public static HotDrinkMachine reflectionInit() throws Exception {
            final Set<Class<? extends HotDrinkFactory>> types = new Reflections("factory.")
                    .getSubTypesOf(HotDrinkFactory.class);

            for (Class<? extends HotDrinkFactory> type : types) {
                namedFactories.add(Pair.of(
                        type.getSimpleName().replace("Factory", ""),
                        type.getDeclaredConstructor().newInstance()
                ));
            }
            return new HotDrinkMachine();
        }

        public static HotDrinkMachine enumInit() {
            List.of(Factories.values()).stream().forEach(x -> namedFactories.add(Pair.of(x.getName(), x.getFactory())));
            return new HotDrinkMachine();
        }
    }
}


class DemoDrinkMachine {

    public static void main(String[] args) throws Exception {

        //reflections
        final HotDrinkMachine reflectionInit = HotDrinkMachine.InitFactory.reflectionInit();
        final HotDrink anotherDrink = reflectionInit.makeDrink();
        anotherDrink.consume();

        //enum
        final HotDrinkMachine enumInit = HotDrinkMachine.InitFactory.enumInit();
        final HotDrink drink = enumInit.makeDrink();
        drink.consume();
    }
}