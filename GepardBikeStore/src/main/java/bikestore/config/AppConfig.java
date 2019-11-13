package bikestore.config;

import bikestore.entities.*;
import bikestore.repositories.HibernateRepository;
import bikestore.repositories.base.GenericRepository;
import bikestore.utils.validators.BikeValidator;
import bikestore.utils.validators.PartValidator;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import bikestore.utils.validators.base.Validator;

@Configuration
public class AppConfig {
    @Bean
    @Autowired
    GenericRepository<Bike> provideBikesGenericRepository(SessionFactory sessionFactory) {
        HibernateRepository<Bike> repo = new HibernateRepository<>(sessionFactory);
        repo.setEntityClass(Bike.class);

        return repo;
    }

    @Bean
    @Autowired
    GenericRepository<Part> providePartsGenericRepository(SessionFactory sessionFactory) {
        HibernateRepository<Part> repo = new HibernateRepository<>(sessionFactory);
        repo.setEntityClass(Part.class);

        return repo;
    }

    @Bean
    @Autowired
    GenericRepository<BikeCart> provideBikeCartsGenericRepository(SessionFactory sessionFactory) {
        HibernateRepository<BikeCart> repo = new HibernateRepository<>(sessionFactory);
        repo.setEntityClass(BikeCart.class);

        return repo;
    }

    @Bean
    @Autowired
    GenericRepository<PartCart> providePartCartsGenericRepository(SessionFactory sessionFactory) {
        HibernateRepository<PartCart> repo = new HibernateRepository<>(sessionFactory);
        repo.setEntityClass(PartCart.class);

        return repo;
    }

    @Bean
    @Autowired
    GenericRepository<User> provideUsersGenericRepository(SessionFactory sessionFactory) {
        HibernateRepository<User> repo = new HibernateRepository<>(sessionFactory);
        repo.setEntityClass(User.class);

        return repo;
    }

    @Bean
    @Autowired
    GenericRepository<Request> provideRequestsGenericRepository(SessionFactory sessionFactory) {
        HibernateRepository<Request> repo = new HibernateRepository<>(sessionFactory);
        repo.setEntityClass(Request.class);

        return repo;
    }

    @Bean
    SessionFactory provideSessionFactory() {
        return HibernateUtils.getSessionFactory();
    }

    @Bean
    Validator<Bike> provideBikeValidator() {
        return new BikeValidator();
    }

    @Bean
    Validator<Part> providePartValidator() {
        return new PartValidator();
    }
}
