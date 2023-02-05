package efrei.carrental.model.repo;

import efrei.carrental.model.jpa.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Integer>, JpaSpecificationExecutor<Car> {
    public Optional<Car> findById(Integer id);

    public Optional<Car> findByModel(String model);

    public List<Car> findAllByBrand(String brand);

    public List<Car> findAll();

    public List<Car> findAllByOfferFeeIsNotNull();

}
