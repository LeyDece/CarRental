package efrei.carrental.service;


import efrei.carrental.model.jpa.Applicationuser;
import efrei.carrental.model.repo.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ApplicationUserService {

    @Autowired
    private ApplicationUserRepository userRepository;

    public Optional<Applicationuser> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public Applicationuser createUser(Applicationuser user) {
        userRepository.save(user);
        return user;
    }

    public Optional<Applicationuser> getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }



}
