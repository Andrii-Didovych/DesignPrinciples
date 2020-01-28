package creational.prototype.constructor;

public class Address {
     public String streetAddress, city, country;

    public Address(String streetAddress, String city, String country) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.country = country;
    }

    public Address(Address other) {
        this(other.streetAddress, other.city, other.country);
    }

    @Override
    public String toString() {
        return "Address{" +
                "streetAddress='" + streetAddress + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}

class Person {
    String name;
    Address address;

    public Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public Person(Person other) {
        name = other.name;
        address = new Address(other.address);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}

class Demo {
    public static void main(String[] args) {
        Person john = new Person("John", new Address("123 London road", "London", "UK"));
        Person chris = new Person(john);

        chris.name = "Chris";

        System.out.println(john);
        System.out.println(chris);
    }
}