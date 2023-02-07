package efrei.carrental.application.rest.controller;

import efrei.carrental.application.exceptions.AppExceptionCode;
import efrei.carrental.application.exceptions.AppException;
import efrei.carrental.application.dto.CarDto;
import efrei.carrental.external.entity.Car;
import efrei.carrental.domain.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/rest/catalog")
public class CatalogController {

    @Autowired
    CatalogService catalogService;

    @PostMapping("/cars")
    @PreAuthorize("hasAnyAuthority('ROLE_AGENT')")
    List<Car> createCarByModel(@RequestBody List<Car> cars) {
        return catalogService.createCars(cars);
    }

    @GetMapping("/cars/model/{model}")
    Car getCarByModel(@PathVariable("model") String model) {
        return catalogService.getCarByModel(model).orElseThrow(() -> new AppException(HttpStatus.BAD_REQUEST, AppExceptionCode.CAR_NOT_FOUND, "Model does not exist"));
    }

    @GetMapping("/cars/brand/{brand}")
    List<Car> getCarByBrand(@PathVariable("brand") String brand) {
        return catalogService.getCarByBrand(brand);
    }

    @GetMapping("/cars")
    List<Car> getAllCars() {
        return catalogService.getAllCars();
    }

    @GetMapping("/cars/offers")
    List<Car> getAllOffers() {
        return catalogService.getAllOffers();
    }

    @GetMapping("/cars/availability/{model}")
    @PreAuthorize("hasAnyAuthority('ROLE_AGENT')")
    Boolean checkAvailabilityOfSpecificCar(@PathVariable("model") String model) {
        var car = catalogService.getCarByModel(model);
        System.out.println(car);
        if (car == null || car.isEmpty()) {
            throw new AppException(HttpStatus.BAD_REQUEST, AppExceptionCode.CAR_NOT_FOUND, "Model does not exist");
        }
        return car.get().isAvailability();
    }

    @PostMapping("/cars/search")
    ResponseEntity<List<Car>> getCarByCriteria(@RequestBody CarDto car) {
        var cars = catalogService.getByCriteria(car);
        return new ResponseEntity(cars, HttpStatus.OK);
    }
}