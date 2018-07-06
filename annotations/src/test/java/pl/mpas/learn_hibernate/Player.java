package pl.mpas.learn_hibernate;

import javax.persistence.*;

@Entity
@Table(name = "Player")
public class Player {
    private Long id;
    private String name;
    private String surname;
    private int age;
    private long kasaMisia;

    public Player() {
    }

    public Player(String name, String surname, int age, long kasaMisia) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.kasaMisia = kasaMisia;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "IMIE", length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getSalary() {
        return kasaMisia;
    }

    public void setSalary(long kasaMisia) {
        this.kasaMisia = kasaMisia;
    }
}
