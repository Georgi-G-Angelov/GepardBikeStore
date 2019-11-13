package bikestore.config;

import bikestore.entities.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtils {
    static SessionFactory sessionFactory;

    static {
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration()
            .configure();

        configuration.addAnnotatedClass(Bike.class);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Part.class);
        configuration.addAnnotatedClass(BikeCart.class);
        configuration.addAnnotatedClass(PartCart.class);
        configuration.addAnnotatedClass(Request.class);

        StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();

        serviceRegistryBuilder.applySettings(configuration.getProperties());
        StandardServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
