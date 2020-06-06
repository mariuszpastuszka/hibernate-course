package pl.mpas.learn_hibernate;

import junit.framework.TestCase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Date;
import java.util.List;

public class NativeApiIllustrationTest extends TestCase {
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

    @SuppressWarnings("unchecked")
    public void testBasicUsage() {
        // create a couple of events...
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Event one = new Event("Our very first event!", new Date());

        System.out.println(one);
        session.save(one);
        System.out.println(one);

        Event secondEvent = new Event("A follow up event", new Date());
        System.out.println("Before save: " + secondEvent);
        session.save(secondEvent);
        System.out.println("After save: " + secondEvent);

        session.save(new Event("Dziś jest mecz!", new Date()));
        session.getTransaction().commit();
        session.close();

        session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery("from Event").list();
        for (Event event : (List<Event>) result) {
            System.out.println("Event (" + event.getDate() + ") : " + event.getTitle());
        }
        session.getTransaction().commit();
        session.close();

        session = sessionFactory.openSession();
        session.beginTransaction();
        Person me = new Person("Mariusz", "P.", 10);
        System.out.println("me before save: " + me);
        session.save(me);
        System.out.println("me after save: " + me);
        session.getTransaction().commit();
        session.close();

        session = sessionFactory.openSession();
        session.beginTransaction();

        Dog myDog = new Dog(null, "Azor", 5);
        System.out.println("My dog before saving: " + myDog);
        session.save(myDog);
        System.out.println("My dog after saving: " + myDog);

        session.close();

    }
}
