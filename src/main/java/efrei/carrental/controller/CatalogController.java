package efrei.carrental.controller;


import efrei.carrental.commons.AppExceptionCode;
import efrei.carrental.exceptions.AppException;
import efrei.carrental.model.dto.CarDto;
import efrei.carrental.model.jpa.CarJpa;
import efrei.carrental.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/rest/catalog")
public class CatalogController {

    @Autowired
    CatalogService catalogService;

    @PostMapping("/cars")
    List<CarJpa> getCarByModel(@RequestBody List<CarJpa> cars) {
        return catalogService.createCars(cars);
    }

    @GetMapping("/cars/model/{model}")
    CarJpa getCarByModel(@PathVariable("model") String model) {
        return catalogService.getCarByModel(model).orElseThrow(() -> new AppException(HttpStatus.BAD_REQUEST, AppExceptionCode.CAR_NOT_FOUND, "Model does not exist"));
    }

    @GetMapping("/cars/brand/{brand}")
    List<CarJpa> getCarByBrand(@PathVariable("brand") String brand) {
        return catalogService.getCarByBrand(brand);
    }

    @GetMapping("/cars")
    List<CarJpa> getAllCars() {
        return catalogService.getAllCars();
    }

    @GetMapping("/cars/offers")
    List<CarJpa> getAllOffers() {
        return catalogService.getAllOffers();
    }

    @GetMapping("/cars/availability/{model}")
    Boolean checkAvailabilityOfSpecificCar(@PathVariable("model") String model) {
        var car = catalogService.getCarByModel(model);
        System.out.println(car);
        if (car == null || car.isEmpty()) {
            throw new AppException(HttpStatus.BAD_REQUEST, AppExceptionCode.CAR_NOT_FOUND, "Model does not exist");
        }
        return car.get().isAvailability();
    }

    @PostMapping("/cars/search")
    ResponseEntity<List<CarJpa>> getCarByCriteria(@RequestBody CarDto car) {
        var cars = catalogService.getByCriteria(car);
        return new ResponseEntity(cars, HttpStatus.OK);
    }
}