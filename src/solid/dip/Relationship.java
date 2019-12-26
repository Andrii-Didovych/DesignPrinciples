package solid.dip;

/*

A. High level modules should not depend on low-level modules.
   Both should depend on abstractions.

B. Abstractions should not depend on details.
   Details should depends on abstractions

 */

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public enum Relationship {
    PARENT,
    CHILD,
    SIBLING
}

class Person {
    public String name;

    public Person(String name) {
        this.name = name;
    }
}

interface RelationshipBrowser {
    List<Person> findAllChildrenOf(String name);
    void addParentAndChild(Person parent, Person child);
}

class RelationshipsTriple implements RelationshipBrowser// low-level
{
    private List<Triple<Person, Relationship, Person>> relations = new ArrayList<>();

    @Override
    public void addParentAndChild(Person parent, Person child) {
        relations.add(Triple.of(parent, Relationship.PARENT, child));
        relations.add(Triple.of(child, Relationship.CHILD, parent));
    }

    @Override
    public List<Person> findAllChildrenOf(String name) {
        return relations.stream()
                .filter(x -> Objects.equals(x.getLeft().name, name) && x.getMiddle() == Relationship.PARENT)
                .map(Triple::getRight)
                .collect(Collectors.toList());
    }


//    public List<Triple<Person, Relationship, Person>> getRelations() {
//        return relations;
//    }

}

class RelationshipPair implements RelationshipBrowser //low-level
{
    private List<Pair<Pair<Person, Person>, Relationship>> relations = new ArrayList<>();

    @Override
    public void addParentAndChild(Person parent, Person child) {
        relations.add(Pair.of(Pair.of(parent, child), Relationship.PARENT));
        relations.add(Pair.of(Pair.of(child, parent), Relationship.CHILD));
    }

    @Override
    public List<Person> findAllChildrenOf(String name) {
        return relations.stream()
                .filter(x -> Objects.equals(x.getLeft().getLeft().name, name) && x.getRight() == Relationship.PARENT)
                .map(x -> x.getLeft().getRight())
                .collect(Collectors.toList());
    }

}


class Research // high-level
{
    public Research(RelationshipBrowser browser) {
        final List<Person> children = browser.findAllChildrenOf("John");
        children.forEach(child -> System.out.println("John has a child called " + child.name));
    }

//    public Research(RelationshipsTriple relationships) {
//        final List<Triple<Person, Relationship, Person>> relations = relationships.getRelations();
//        relations.stream()
//                .filter(x -> x.getLeft().name.equals("John") && x.getMiddle() == Relationship.PARENT)
//                .forEach(ch -> System.out.println("John has a child called " + ch.getRight().name));
//    }

}

class Demo {

    public static void main(String[] args) {
        final Person john = new Person("John");
        final Person chris = new Person("Chris");
        final Person matt = new Person("Matt");

        RelationshipBrowser relationships = new RelationshipPair(); // work the same as with RelationshipsTriple
        relationships.addParentAndChild(john, chris);
        relationships.addParentAndChild(john, matt);

        new Research(relationships);
    }
}