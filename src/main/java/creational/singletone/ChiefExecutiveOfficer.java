package creational.singletone;

public class ChiefExecutiveOfficer {

    private static String name;
    private static int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        ChiefExecutiveOfficer.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        ChiefExecutiveOfficer.age = age;
    }

    @Override
    public String toString() {
        return String.format("%s,%s", name, age);
    }
}

class MonostateDemo {
    public static void main(String[] args) {
        ChiefExecutiveOfficer ceo1 = new ChiefExecutiveOfficer();
        ceo1.setAge(12);
        ceo1.setName("Andrew");

        ChiefExecutiveOfficer ceo2 = new ChiefExecutiveOfficer();
        System.out.println(ceo2);
    }
}
