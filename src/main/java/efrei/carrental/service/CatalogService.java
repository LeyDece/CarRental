package efrei.carrental.service;

import efrei.carrental.model.jpa.CarJpa;
import efrei.carrental.model.repo.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CatalogService {

    @Autowired
    CarRepository carRepository;

    public Optional<CarJpa> getCarByModel(String model) {
        return carRepository.findByModel(model);
    }

    public List<CarJpa> getCarByBrand(String brand) {
        return carRepository.findAllByBrand(brand);
    }

    public List<CarJpa> getAllCars(){
        return carRepository.findAll();
    }

    public List<CarJpa> createCars(List<CarJpa> cars){
        return carRepository.saveAll(cars);
    }

}
