package pl.mpas.jvm;

import java.util.HashSet;
import java.util.Set;

public class EqualsProblem {

    public static void main(String[] args) {
        Set<Person> persons = new HashSet<>();
        Person one = new Person("maniek");
        Person two = new Person("maniek");

        System.out.println("one.equals(two)? " + (one.equals(two)));
        persons.add(one);
        persons.add(two);

        System.out.println("set size: " + persons.size());
    }
}
