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

    public Optional<ApplicationuserJpa> getUser(Integer id){
        return userRepository.findById(id);
    }
}
