package bikestore.services.base;


import org.springframework.security.core.userdetails.UserDetailsService;
import bikestore.entities.User;

import java.util.List;
public interface UsersService extends UserDetailsService {

    User getUserByUsername(String username);

    void create(User user);

    public List<User> getAll();


}
