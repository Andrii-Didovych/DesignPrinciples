package creational.prototype;

import java.util.Arrays;

public class Address{

    public String streetName;
    public int houseNumber;

    public Address(String streetName, int houseNumber) {
        this.streetName = streetName;
        this.houseNumber = houseNumber;
    }

    @Override
    public String toString() {
        return "Address{" +
                "streetName='" + streetName + '\'' +
                ", houseNumber=" + houseNumber +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Address(streetName, houseNumber);
    }
}

class Person{
    public String [] names;
    public Address address;

    public Person(String[] names, Address address) {
        this.names = names;
        this.address = address;
    }

    //deep copy
    @Override
    public String toString() {
        return "Person{" +
                "names=" + Arrays.toString(names) +
                ", address=" + address +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Person(
                names.clone(),
                (Address) address.clone());
    }
}


class Demo {

    public static void main(String[] args) throws CloneNotSupportedException {
        final Person john = new Person(new String[]{"John", "Smith"}, new Address("London Road", 123));

        //reference to the same object
        Person jane = john;
        jane.names[0] = "Jane";
        jane.address.houseNumber = 124;

        //deep copy, not reliable
        Person ross = (Person) john.clone();
        ross.names[0] = "Ross";
        ross.address.houseNumber = 125;

        System.out.println(john);
        System.out.println(jane);
        System.out.println(ross);


    }
}