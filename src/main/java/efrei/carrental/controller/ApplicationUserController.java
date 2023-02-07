package efrei.carrental.controller;

import efrei.carrental.commons.AppExceptionCode;
import efrei.carrental.exceptions.AppException;
import efrei.carrental.model.dto.RentalDto;
import efrei.carrental.model.dto.RentalRequestBodyDto;
import efrei.carrental.model.jpa.Applicationuser;
import efrei.carrental.service.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.session.SessionRepository;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.session.web.http.SessionRepositoryFilter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/rest/users")
@EnableJdbcHttpSession
public class ApplicationUserController {

    @Autowired
    ApplicationUserService applicationUserService;

    @Autowired
    SessionRepository sessions;

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

    @PostMapping("/cart/add")
    public ResponseEntity addRentalToCart(@RequestBody RentalRequestBodyDto rental) {
        applicationUserService.addRentalToCart(rental.getRentalDto(), rental.getCustomerId());
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/cart/{id}")
    public ResponseEntity<List<RentalDto>> getCartContent(@PathVariable("id") int customerId) {
        var content = applicationUserService.getCartContent(customerId);
        return new ResponseEntity(content, HttpStatus.OK);
    }

    @GetMapping("/cart/clear/{id}")
    public ResponseEntity clearCart(@PathVariable("id") int customerId) {
        applicationUserService.clearCart(customerId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/cart/submit/{id}")
    public ResponseEntity submitCart(@PathVariable("id") int customerId) {
        applicationUserService.submitCart(customerId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/sessions")
    public ResponseEntity getSession() {
        var session = sessions.createSession();
        session.setAttribute("user_id", "test_user");
        sessions.save(session);

        return new ResponseEntity(session, HttpStatus.OK);
    }

}
