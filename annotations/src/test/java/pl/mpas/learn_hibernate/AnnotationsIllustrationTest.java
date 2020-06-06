package pl.mpas.learn_hibernate;

import junit.framework.TestCase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import pl.mpas.learn_hibernate.converter.Address;
import pl.mpas.learn_hibernate.converter.SwissZipcode;
import pl.mpas.learn_hibernate.converter.User;
import pl.mpas.learn_hibernate.converter.Zipcode;
import pl.mpas.learn_hibernate.inheritance.singletable.BankAccount;
import pl.mpas.learn_hibernate.inheritance.singletable.BillingDetails;
import pl.mpas.learn_hibernate.inheritance.singletable.CreditCard;
import pl.mpas.learn_hibernate.my_converter.PlayerWithSecret;
import pl.mpas.learn_hibernate.my_converter.Secret;
import pl.mpas.learn_hibernate.onetomany.Cart;
import pl.mpas.learn_hibernate.onetomany.Items;

import java.util.*;

public class AnnotationsIllustrationTest extends TestCase {
    private SessionFactory sessionFactory;

    @Override
    protected void setUp() throws Exception {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    @Override
    protected void tearDown() throws Exception {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    public void testDogSaving() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Dog dog = new Dog("Azor", 1);
        System.out.println("Dog - before saving: " + dog);
        session.save(dog);
        dog.setAge(2);
        session.save(dog);
        System.out.println("Dog - after saving: " + dog);

        session.getTransaction().commit();
        session.close();

    }

    public void testUserWithZipCode() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Zipcode zipcode = new SwissZipcode("12345");
        Address address = new Address("Warszawska", zipcode, "Warszawa");
        User user = new User();
        user.setUsername("Mariusz");
        user.setHomeAddress(address);

        session.save(user);

        session.getTransaction().commit();
        session.close();
    }

    public void testPlayerSecret() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        PlayerWithSecret zero = new PlayerWithSecret("XXXXL",
                new Secret("mam sekret"));

        session.save(zero);

        session.getTransaction().commit();
        session.close();
    }

    public void testSimpleSearching() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, 10, 17);
        Date eventDate = calendar.getTime();

        Event first = new Event("Our very first event!", new Date());
        session.save(first);
        session.save(new Event("A follow up event", new Date()));

        first.setTitle("New title");
        session.getTransaction().commit();

        session.close();

        session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery("select e from Event e where e.title = :title")
                .setParameter("title", "Our very first event!")
                .list();
        for (Event event : (List<Event>) result) {
            System.out.println("Event (" + event.getDate() + ") : " + event.getTitle());
        }
        session.getTransaction().commit();
        session.close();
    }

    public void testInheritance() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        BillingDetails ba = new BankAccount("Mariusz", "00077", "NBP", "01234");
        BillingDetails cc = new CreditCard("Me", "12345", "grudzień", "3000");
        session.save(ba);
        session.save(cc);

        session.getTransaction().commit();
        session.close();


        session = sessionFactory.openSession();
        List<BillingDetails> billingDetails = session.createQuery("from BillingDetails", BillingDetails.class).list();
        for (BillingDetails bd : billingDetails) {
            System.out.println("Reading BillingDetails from db: " + bd);
        }
        session.close();
    }

    public void testStoringPersons() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(new Person("Mariusz", "P.", 1));
        session.save(new Person("Alicja", "O.", 1));
        session.save(new Pet("Burek", "Dog", 15));
        session.save(new Player("Zenek", "Baggio", 25, 1_000_000));
        session.getTransaction().commit();
        session.close();

        session = sessionFactory.openSession();
        List<Person> personsFromDb = session.createQuery("from Person", Person.class).list();
        for (Person person : personsFromDb) {
            System.out.println("Reading person from db: " + person);
        }
        session.close();
    }

    public void testOneToMany() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Cart cart = new Cart();
        Set<Items> items = new HashSet<>();
        items.add(new Items(cart));
        items.add(new Items(cart));
        items.add(new Items(cart));
        items.add(new Items(cart));
        cart.setItems(items);

        session.save(cart);

        session.getTransaction().commit();
        session.close();

    }

    @SuppressWarnings({"unchecked"})
    public void testBasicUsage() {
        // create a couple of events...
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(new Event("Our very first event!", new Date()));
        session.save(new Event("A follow up event", new Date()));
        session.getTransaction().commit();
        session.close();

        // now lets pull events from the database and list them
        session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery("from Event").list();
        for (Event event : (List<Event>) result) {
            System.out.println("Event (" + event.getDate() + ") : " + event.getTitle());
        }
        session.getTransaction().commit();
        session.close();
    }
}
