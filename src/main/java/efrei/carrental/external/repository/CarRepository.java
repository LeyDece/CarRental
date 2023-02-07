package efrei.carrental.external.repository;

import efrei.carrental.external.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer>, JpaSpecificationExecutor<Car> {
    public Optional<Car> findById(Integer id);

    public Optional<Car> findByModel(String model);

    public List<Car> findAllByBrand(String brand);

    public List<Car> findAll();

    public List<Car> findAllByOfferFeeIsNotNull();

}
