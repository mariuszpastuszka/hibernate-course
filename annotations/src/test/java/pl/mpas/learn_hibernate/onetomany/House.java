package pl.mpas.learn_hibernate.onetomany;

import pl.mpas.learn_hibernate.Person;

import javax.persistence.*;
import java.util.List;

// 1). Entity - managed by Hibernate
@Entity
public class House {

    // 2). Must have id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    List<Person> owners;

    public List<Person> getOwners() {
        return owners;
    }

    public void setOwners(List<Person> owners) {
        this.owners = owners;
    }
    // 3). no-args constructor

    public House() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
