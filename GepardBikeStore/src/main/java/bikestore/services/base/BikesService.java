package bikestore.services.base;

import bikestore.entities.Bike;

import java.io.InvalidObjectException;
import java.util.List;

public interface BikesService {
    List<Bike> getAllBikes();

    List<Bike> getBikesByCategory(String category);

    Bike getBikeById(int id);

}
