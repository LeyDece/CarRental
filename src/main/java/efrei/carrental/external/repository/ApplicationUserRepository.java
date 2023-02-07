package efrei.carrental.external.repository;

import efrei.carrental.external.entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Integer> {
    public Optional<ApplicationUser> findById(Integer id);

    public Optional<ApplicationUser> findByUsername(String name);

}
