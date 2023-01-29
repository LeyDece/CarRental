package efrei.carrental.model.repo;

import efrei.carrental.model.jpa.ApplicationuserJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationuserJpa, Integer> {
    public Optional<ApplicationuserJpa> findById(Integer id);

    public Optional<ApplicationuserJpa> findByUsername(String name);

}
