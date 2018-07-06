package pl.mpas.learn_hibernate.onetomany;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "CART")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private long id;


    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private Set<Items> items;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Set<Items> getItems() {
        return items;
    }

    public void setItems(Set<Items> items) {
        this.items = items;
    }

    public static void main(String[] args) {
        Cart cart = new Cart();
        Set<Items> items = new HashSet<>();
                items.add(new Items(cart));
        cart.setItems(items);
    }
}