package pl.mpas.learn_hibernate.onetoone;


import java.util.List;

import javax.persistence.*;

@Entity
public class Library {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column
    @Basic
    private String name;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    public Library() {
    }

    public Library(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}
