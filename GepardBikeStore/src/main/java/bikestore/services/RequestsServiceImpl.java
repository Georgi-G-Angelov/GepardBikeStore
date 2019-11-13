package bikestore.services;

import bikestore.entities.Request;
import bikestore.repositories.base.GenericRepository;
import bikestore.services.base.RequestsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestsServiceImpl implements RequestsService {

    private final GenericRepository<Request> requestsRepository;

    public RequestsServiceImpl(GenericRepository<Request> requestsRepository) {
        this.requestsRepository = requestsRepository;
    }

    @Override
    public List<Request> getAllRequests() {
        return requestsRepository.getAll();
    }

    @Override
    public void createRequest(Request request) {
        requestsRepository.create(request);
    }
}
