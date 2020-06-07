package pl.mpas.learn_hibernate;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity(name = "My_DOG")
@Table(name = "Dogs")
public class Dog {

    @Id

    @GeneratedValue(generator = "increment")

    @GenericGenerator(name = "increment", strategy = "increment")
    Long Id;
    @Column(name = "Name")

    String name;
    @Column(name = "Age")
    int age;


    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
    @OneToOne(mappedBy = "dog")

    private Person owner;

    public Dog()

    {
    }


    public Dog(String name, int age) {


        this.name = name;

        this.age = age;

    }



    public Long getId() {

        return Id;

    }



    public String getName() {

        return name;

    }



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
