package efrei.carrental.controller;


import efrei.carrental.model.jpa.CarJpa;
import efrei.carrental.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/cars/{model}")
    CarJpa getCarByModel(@PathVariable("model") String model) {
        return catalogService.getCarByModel(model).orElse(null);
    }

    @GetMapping("/cars/{brand}")
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
}