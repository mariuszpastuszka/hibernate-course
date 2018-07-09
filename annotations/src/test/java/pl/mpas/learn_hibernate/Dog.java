package pl.mpas.learn_hibernate;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity(name = "My_DOG")
@Table(name = "Dogs")
public class Dog {


    Long Id;
    String name;
    int age;

    public Dog()

    {
    }


    public Dog(String name, int age) {


        this.name = name;

        this.age = age;

    }

    @Id

    @GeneratedValue(generator = "increment")

    @GenericGenerator(name = "increment", strategy = "increment")

    public Long getId() {

        return Id;

    }


    @Column(name = "Name")

    public String getName() {

        return name;

    }


    @Column(name = "Age")

    public int getAge() {

        return age;

    }

    public void setId(Long id) {
        Id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
}