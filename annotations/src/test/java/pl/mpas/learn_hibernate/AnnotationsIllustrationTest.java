package pl.mpas.learn_hibernate;

import junit.framework.TestCase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import pl.mpas.learn_hibernate.inheritance.singletable.BankAccount;
import pl.mpas.learn_hibernate.inheritance.singletable.BillingDetails;
import pl.mpas.learn_hibernate.inheritance.singletable.CreditCard;
import pl.mpas.learn_hibernate.onetomany.Cart;
import pl.mpas.learn_hibernate.onetomany.Items;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
