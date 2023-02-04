package efrei.carrental.model.repo;

import efrei.carrental.model.jpa.CarJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<CarJpa, Integer>, JpaSpecificationExecutor<CarJpa> {
    public Optional<CarJpa> findById(Integer id);

    public Optional<CarJpa> findByModel(String model);

    public List<CarJpa> findAllByBrand(String brand);

    public List<CarJpa> findAll();

    public List<CarJpa> findAllByOfferFeeIsNotNull();

}
