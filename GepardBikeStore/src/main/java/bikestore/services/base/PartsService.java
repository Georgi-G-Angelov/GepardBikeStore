package bikestore.services.base;

import bikestore.entities.Part;

import java.io.InvalidObjectException;
import java.util.List;

public interface PartsService {
    List<Part> getAllParts();

    List<Part> getPartsByCategory(String category);

    Part getPartById(int id);

}