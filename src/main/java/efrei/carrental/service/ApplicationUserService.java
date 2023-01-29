package efrei.carrental.service;


import efrei.carrental.model.jpa.ApplicationuserJpa;
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

    public Optional<ApplicationuserJpa> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public ApplicationuserJpa createUser(ApplicationuserJpa user) {
        userRepository.save(user);
        return user;
    }

    public Optional<ApplicationuserJpa> getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }



}
