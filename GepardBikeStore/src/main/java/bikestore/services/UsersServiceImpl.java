package bikestore.services;

import bikestore.entities.*;
import bikestore.repositories.base.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import bikestore.services.base.UsersService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    private final GenericRepository<User> usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersServiceImpl(GenericRepository<User> usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getUserByUsername(String username) {
        User user = usersRepository.getAll()
            .stream()
            .filter(u -> u.getUsername().equals(username))
            .findFirst()
            .orElse(null);

        return user;
    }

    @Override
    public void create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usersRepository.create(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByUsername(username);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }

    @Override
    public List<User> getAll(){
        return usersRepository.getAll();
    }
}
