package efrei.carrental.controller;

import efrei.carrental.commons.AppExceptionCode;
import efrei.carrental.exceptions.AppException;
import efrei.carrental.model.dto.RentalRequestBodyDto;
import efrei.carrental.model.jpa.Applicationuser;
import efrei.carrental.service.ApplicationUserService;
import efrei.carrental.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/rest/users")
public class ApplicationUserController {

    @Autowired
    ApplicationUserService applicationUserService;

    @Autowired
    RentalService rentalService;

    @GetMapping("/{id}")
    public Applicationuser getUserById(@PathVariable("id") int id) {
        var user = applicationUserService.getUserById(id);
        return user.orElse(null);
    }

    @PostMapping("")
    public Applicationuser createUser(@RequestBody Applicationuser user) throws AppException {
        try {
            applicationUserService.createUser(user);
        } catch (Exception e) {
            throw new AppException(HttpStatus.BAD_REQUEST, AppExceptionCode.USER_NOT_FOUND, "User already exists");
        }
        return user;
    }

    @GetMapping("/getusername/{username}")
    public Applicationuser getByUsername(@PathVariable("username") String username) {
        var user = applicationUserService.getUserByUsername(username);
        return user.orElse(null);
    }

    @GetMapping("/{username}/isRegistered")
    public Boolean isRegistered(@PathVariable("username") String username) {
        var user = applicationUserService.getUserByUsername(username);
        return user.isPresent();
    }

    @PostMapping("/addRental")
    public ResponseEntity addRentalToCart(@RequestBody RentalRequestBodyDto rental) {
        rentalService.addRentalToCart(rental.getRentalDto(), rental.getCustomerId());
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/")
    public String hello() {
        System.out.println("IN GET USER");
        return "hello";
    }
}
