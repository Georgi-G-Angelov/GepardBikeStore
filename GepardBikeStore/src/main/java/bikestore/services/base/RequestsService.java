package bikestore.services.base;

import bikestore.entities.Request;
import bikestore.repositories.base.GenericRepository;

import java.util.List;

public interface RequestsService {

    List<Request> getAllRequests();

    void createRequest(Request request);
}
