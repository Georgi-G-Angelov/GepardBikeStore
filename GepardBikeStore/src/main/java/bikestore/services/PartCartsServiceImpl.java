package bikestore.services;

import bikestore.entities.Part;
import bikestore.entities.PartCart;
import bikestore.repositories.base.GenericRepository;
import bikestore.services.base.PartCartsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PartCartsServiceImpl implements PartCartsService {

    private final GenericRepository<Part> partsRepository;
    private final GenericRepository<PartCart> partCartsRepository;

    public PartCartsServiceImpl(GenericRepository<Part> partsRepository, GenericRepository<PartCart> partCartsRepository) {
        this.partsRepository = partsRepository;
        this.partCartsRepository = partCartsRepository;
    }

    @Override
    public List<PartCart> getAllPartCarts() {
        return partCartsRepository.getAll();
    }

    @Override
    public List<PartCart> getPartCartsByUserId(int id) {
        List<PartCart> carts = getAllPartCarts();
        List<PartCart> byId = new ArrayList<>();
        for(PartCart partCart : carts){
            if(partCart.getUserId()==id){
                byId.add(partCart);
            }
        }
        return byId;
    }

    @Override
    public List<Part> getPartsByUserId(int id) {
        List<PartCart> parts = getPartCartsByUserId(id);
        List<Part> byId = new ArrayList<>();
        for(PartCart partCart : parts){
            byId.add(partsRepository.getById(partCart.getPartId()));
        }
        return byId;
    }

    @Override
    public void createPartCart(PartCart partCart) {
        partCartsRepository.create(partCart);
    }

    @Override
    public void deleteByUserId(int user_id) {
        for (PartCart partCart : partCartsRepository.getAll()){
            if(partCart.getUserId()==user_id){
                partCartsRepository.delete(partCart);
            }
        }
    }
}
