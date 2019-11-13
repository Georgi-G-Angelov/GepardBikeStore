package bikestore.services.base;

import bikestore.entities.Bike;
import bikestore.entities.BikeCart;

import java.util.List;

public interface BikeCartsService {
    List<BikeCart> getAllBikeCarts();

    List<BikeCart> getBikeCartsByUserId(int id);

    List<Bike> getBikesByUserId(int id);

    void createBikeCart(BikeCart bikeCart);

    void deleteByUserID(int user_id);


}
