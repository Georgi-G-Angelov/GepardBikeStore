package bikestore.services;

import bikestore.entities.Part;
import bikestore.repositories.base.GenericRepository;
import bikestore.services.base.PartsService;
import bikestore.utils.validators.base.Validator;
import org.springframework.stereotype.Service;

import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.List;


@Service
public class PartsServiceImpl implements PartsService {
    private static final int PAGE_SIZE = 10;
    private static final int PRODUCT_NAME_MIN_LENGTH = 4;
    private final GenericRepository<Part> partsRepository;
    private final Validator<Part> partValidator;

    public PartsServiceImpl(GenericRepository<Part> partsRepository, Validator<Part> partValidator) {
        this.partsRepository = partsRepository;
        this.partValidator = partValidator;
    }

    @Override
    public List<Part> getAllParts() {
        return partsRepository.getAll();
    }

    @Override
    public List<Part> getPartsByCategory(String categoryName) {
        List<Part> parts = partsRepository.getAll();
        ArrayList<Part> bikesByCategory = new ArrayList<>();
        for(int i=0;i<parts.size();i++) {
            if(parts.get(i).getCategory().equals(categoryName)) {
                bikesByCategory.add(parts.get(i));
            }
        }
        return bikesByCategory;
    }

    @Override
    public Part getPartById(int id) {
        return partsRepository.getById(id);
    }
}
