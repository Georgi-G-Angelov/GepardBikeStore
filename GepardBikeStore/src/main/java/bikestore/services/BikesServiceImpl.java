package bikestore.services;

import bikestore.entities.Bike;
import bikestore.repositories.base.GenericRepository;
import bikestore.services.base.BikesService;
import bikestore.utils.validators.base.Validator;
import org.springframework.stereotype.Service;

import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.List;


@Service
public class BikesServiceImpl implements BikesService {
    private static final int PAGE_SIZE = 10;
    private static final int PRODUCT_NAME_MIN_LENGTH = 4;
    private final GenericRepository<Bike> bikesRepository;
    private final Validator<Bike> bikeValidator;

    public BikesServiceImpl(GenericRepository<Bike> bikesRepository, Validator<Bike> bikeValidator) {
        this.bikesRepository = bikesRepository;
        this.bikeValidator = bikeValidator;
    }

    @Override
    public List<Bike> getAllBikes() {
        return bikesRepository.getAll();
    }

    @Override
    public List<Bike> getBikesByCategory(String categoryName) {
        List<Bike> bikes = bikesRepository.getAll();
        ArrayList<Bike> bikesByCategory = new ArrayList<>();
        for(int i=0;i<bikes.size();i++) {
            if(bikes.get(i).getCategory().equals(categoryName)) {
                bikesByCategory.add(bikes.get(i));
            }
        }
        return bikesByCategory;
    }

    @Override
    public Bike getBikeById(int id) {
        return bikesRepository.getById(id);
    }
}
