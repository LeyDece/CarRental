package efrei.carrental.model.repo;

import efrei.carrental.model.jpa.Applicationuser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationUserRepository extends JpaRepository<Applicationuser, Integer> {
    public Optional<Applicationuser> findById(Integer id);

    public Optional<Applicationuser> findByUsername(String name);

}
