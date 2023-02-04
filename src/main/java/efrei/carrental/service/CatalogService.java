package efrei.carrental.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import efrei.carrental.model.dto.CarDto;
import efrei.carrental.model.jpa.CarJpa;
import efrei.carrental.model.repo.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;

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

    public List<CarJpa> getAllCars() {
        return carRepository.findAll();
    }

    public List<CarJpa> createCars(List<CarJpa> cars) {
        return carRepository.saveAll(cars);
    }

    public List<CarJpa> getAllOffers() {
        return carRepository.findAllByOfferFeeIsNotNull();
    }

    public List<CarJpa> getByCriteria(CarDto car) {

        Specification<CarJpa> predicates =  Specification.where(isAvailable(car.isAvailability()));

        if (car.getModel() != null) {
            predicates = predicates.and(hasModel(car.getModel()));
        }

        if (car.getBrand() != null) {
            predicates.and(hasBrand(car.getBrand()));
        }

        if (car.getYear() != null) {
            predicates.and(inYear(car.getYear()));
        }

        if (car.getRentalFee() != null) {
            predicates.and(isLessThan(car.getRentalFee()));
        }

        return carRepository.findAll(Specification.where(predicates));
    }

    static Specification<CarJpa> hasBrand(String brand) {
        return (car, cq, cb) -> cb.equal(car.get("brand"), brand);
    }

    static Specification<CarJpa> hasModel(String model) {
        return (car, cq, cb) ->  cb.equal(car.get("model"), model);
    }

    static Specification<CarJpa> isAvailable(boolean availability) {
        return (car, cq, cb) -> cb.equal(car.get("availability"), availability);
    }

    static Specification<CarJpa> inYear(int year) {
        return (car, cq, cb) -> cb.equal(car.get("year"), year);
    }

    static Specification<CarJpa> isLessThan(BigDecimal rentalFee) {
        return (car, cq, cb) -> cb.lessThanOrEqualTo(car.get("rentalFee"), rentalFee);
    }
}
