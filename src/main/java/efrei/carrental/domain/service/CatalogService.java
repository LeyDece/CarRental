package efrei.carrental.domain.service;

import efrei.carrental.application.dto.CarDto;
import efrei.carrental.external.entity.Car;
import efrei.carrental.external.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional
public class CatalogService {

    @Autowired
    CarRepository carRepository;

    public Optional<Car> getCarById(int id) {
        return carRepository.findById(id);
    }

    public Optional<Car> getCarByModel(String model) {
        return carRepository.findByModel(model);
    }

    public List<Car> getCarByBrand(String brand) {
        return carRepository.findAllByBrand(brand);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public List<Car> createCars(List<Car> cars) {
        return carRepository.saveAll(cars);
    }

    public List<Car> getAllOffers() {
        return carRepository.findAllByOfferFeeIsNotNull();
    }

    public List<Car> getByCriteria(CarDto car) {

        Specification<Car> predicates =  Specification.where(isAvailable(car.isAvailability()));

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

    static Specification<Car> hasBrand(String brand) {
        return (car, cq, cb) -> cb.equal(car.get("brand"), brand);
    }

    static Specification<Car> hasModel(String model) {
        return (car, cq, cb) ->  cb.equal(car.get("model"), model);
    }

    static Specification<Car> isAvailable(boolean availability) {
        return (car, cq, cb) -> cb.equal(car.get("availability"), availability);
    }

    static Specification<Car> inYear(int year) {
        return (car, cq, cb) -> cb.equal(car.get("year"), year);
    }

    static Specification<Car> isLessThan(BigDecimal rentalFee) {
        return (car, cq, cb) -> cb.lessThanOrEqualTo(car.get("rentalFee"), rentalFee);
    }
}
