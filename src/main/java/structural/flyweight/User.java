package structural.flyweight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class User {

    private String fullName;

    public User(String fullName) {
        this.fullName = fullName;
    }
}

class User2 {

    private static List<String> strings = new ArrayList<>();
    private List<String> names;

    public User2(String fullName) {
        Function<String, String> getOrAdd = (String s) -> {
            int index = strings.indexOf(s);
            if (index != -1) {
                return strings.get(index);
            } else {
                strings.add(s);
                return strings.get(strings.size() - 1);
            }
        };
        names = Arrays.stream(fullName.split(" "))
                .map(s -> getOrAdd.apply(s))
                .collect(Collectors.toList());
    }

    public String getName() {
        return names.get(0) + " " + names.get(1);
    }
}

class Demo {
    public static void main(String[] args) {
        User2 user = new User2("john smith");
        User2 user2 = new User2("jane smith");

        System.out.println(user.getName());
        System.out.println(user2.getName());
    }
}