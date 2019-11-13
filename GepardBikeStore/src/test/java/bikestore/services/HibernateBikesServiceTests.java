package bikestore.services;

import bikestore.entities.Bike;
import bikestore.repositories.base.GenericRepository;
import bikestore.utils.validators.BikeValidator;
import org.junit.Assert;
import org.junit.Test;

import java.io.InvalidObjectException;
import java.util.Arrays;
import java.util.List;

public class HibernateBikesServiceTests {
    @Test
    public void getAllProduct_whenProductsAreAvaible_expectTheProducts() {
        // Arrange
        List<Bike> bikes = Arrays.asList(
            new Bike(),
            new Bike()
        );

        GenericRepository<Bike> repo = new GenericRepository<Bike>() {
            @Override
            public List<Bike> getAll() {
                return bikes;
            }

            @Override
            public Bike getById(int id) {
                return null;
            }

            @Override
            public Bike create(Bike entity) {
                return null;
            }
        };

        BikesServiceImpl service = new BikesServiceImpl(repo, new BikeValidator());

        // Act

        List<Bike> actualBikes = service.getAllProducts();

        // Assert

        Assert.assertArrayEquals(
            actualBikes.toArray(),
            bikes.toArray()
        );
    }

    @Test
    public void getById_whenNoProductWithId_expectNull() {
        // Arrange
        List<Bike> bikes = Arrays.asList(
            new Bike(),
            new Bike()
        );

        GenericRepository<Bike> repo = new GenericRepository<Bike>() {
            @Override
            public List<Bike> getAll() {
                return bikes;
            }

            @Override
            public Bike getById(int id) {
                return null;
            }

            @Override
            public Bike create(Bike entity) {
                return null;
            }
        };

        BikesServiceImpl service = new BikesServiceImpl(repo, new BikeValidator());

        // Act

        Bike bike = service.getProductById(-1);

        // Assert

        Assert.assertNull(bike);
    }

    @Test(expected = Exception.class)
    public void create_whenProductNameWithLen2_expectToThrow() {
        GenericRepository<Bike> repo = new GenericRepository<Bike>() {
            @Override
            public List<Bike> getAll() {
                return null;
            }

            @Override
            public Bike getById(int id) {
                return null;
            }

            @Override
            public Bike create(Bike entity) {
                return null;
            }
        };

        BikesServiceImpl service = new BikesServiceImpl(repo, new BikeValidator());

        Bike bike = new Bike();
        bike.setName("aa");

        // Act
        try {
            service.createProduct(bike);
        } catch (InvalidObjectException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void create_whenProductIsValid_expectToHasId() {
        int id = 1;
        GenericRepository<Bike> repo = new GenericRepository<Bike>() {
            @Override
            public List<Bike> getAll() {
                return null;
            }

            @Override
            public Bike getById(int id) {
                return null;
            }

            @Override
            public Bike create(Bike entity) {
                entity.setId(id);
                return entity;
            }
        };

        BikesServiceImpl service = new BikesServiceImpl(repo, new BikeValidator());

        Bike bike = new Bike();
        bike.setName("aaaa");

        // Act
        try {
            service.createProduct(bike);
        } catch (InvalidObjectException e) {
            e.printStackTrace();
        }

        // Assert

        Assert.assertEquals(id, bike.getId());
    }
}
