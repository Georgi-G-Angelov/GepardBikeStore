package bikestore.services;

import bikestore.entities.Bike;
import bikestore.entities.BikeCart;
import bikestore.repositories.base.GenericRepository;
import bikestore.services.base.BikeCartsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BikeCartsServiceImpl implements BikeCartsService {
    private final GenericRepository<Bike> bikesRepository;
    private final GenericRepository<BikeCart> bikeCartsRepository;

    public BikeCartsServiceImpl(GenericRepository<Bike> bikesRepository, GenericRepository<BikeCart> bikeCartsRepository) {
        this.bikesRepository = bikesRepository;
        this.bikeCartsRepository = bikeCartsRepository;
    }

    @Override
    public List<BikeCart> getAllBikeCarts() {
        return bikeCartsRepository.getAll();
    }

    @Override
    public List<BikeCart> getBikeCartsByUserId(int id) {
        List<BikeCart> carts = getAllBikeCarts();
        List<BikeCart> byId = new ArrayList<>();
        for(BikeCart bikeCart : carts){
            if(bikeCart.getUserId()==id){
                byId.add(bikeCart);
            }
        }
        return byId;
    }

    @Override
    public List<Bike> getBikesByUserId(int id) {
        List<BikeCart> bikes = getBikeCartsByUserId(id);
        List<Bike> byId = new ArrayList<>();
        for(BikeCart bikeCart : bikes){
            byId.add(bikesRepository.getById(bikeCart.getBikeId()));
        }
        return byId;
    }

    @Override
    public void createBikeCart(BikeCart bikeCart) {
        bikeCartsRepository.create(bikeCart);
    }

    @Override
    public void deleteByUserID(int user_id) {
        for (BikeCart bikeCart : bikeCartsRepository.getAll()){
            if(bikeCart.getUserId()==user_id){
                bikeCartsRepository.delete(bikeCart);
            }
        }
    }
}
