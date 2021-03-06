package pl.mpas.learn_hibernate.converter;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "USERS")
public class User implements Serializable {

    @Id
    @GeneratedValue
    protected Long id;

    @NotNull
    protected String username;

    // Group multiple attribute conversions with @Converts
    @Convert(
        converter = ZipcodeConverter.class,
        attributeName = "zipcode" // Or "city.zipcode" for nested embeddables
    )
    protected Address homeAddress;

    public Long getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    // ...
}
