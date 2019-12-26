package builder.fluent;

public class Person {

    public String name;
    public String position;

    @Override
    public String toString() {
        return String.join(" - ", name, position);
    }


    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public Person setPosition(String position) {
        this.position = position;
        return this;
    }
}

class Builder {
    private Person person = new Person();

    public Builder withName(String name) {
        person.name = name;
        return this;
    }

    public Builder worksAt(String position) {
        person.position = position;
        return this;
    }

    public Person build() {
        return person;
    }
}

class PersonBuilder<SELF extends PersonBuilder<SELF>> {

    protected Person person = new Person();

    public SELF withName(String name) {
        person.name = name;
        return (SELF) this;
    }

    public Person build() {
        return person;
    }


}

class EmployeeBuilder extends PersonBuilder<EmployeeBuilder> {

    public EmployeeBuilder worksAt(String position) {
        person.position = position;
        return this;
    }

}

class PersonDemo {

    public static void main(String[] args) {
        //1
        final EmployeeBuilder pb = new EmployeeBuilder();
        Person person = pb
                .withName("John")
                .worksAt("Developer")
                .build();
        System.out.println(person);

        //2
        Builder builder = new Builder();
        Person person1 = builder
                .withName("Jack")
                .worksAt("Dew")
                .build();
        System.out.println(person1);

        //3
        Person person2 = new Person().setName("Bill").setPosition("Tester");
        System.out.println(person2);
    }
}