package bikestore.services.base;

import bikestore.entities.Part;
import bikestore.entities.PartCart;

import java.util.List;

public interface PartCartsService {
    List<PartCart> getAllPartCarts();

    List<PartCart> getPartCartsByUserId(int id);

    List<Part> getPartsByUserId(int id);

    void createPartCart(PartCart partCart);

    void deleteByUserId(int user_id);
}
