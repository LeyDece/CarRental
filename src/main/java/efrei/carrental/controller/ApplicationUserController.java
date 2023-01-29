package efrei.carrental.controller;

import efrei.carrental.Commons.AppExceptionCode;
import efrei.carrental.exceptions.AppException;
import efrei.carrental.model.jpa.ApplicationuserJpa;
import efrei.carrental.service.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/rest/users")
public class ApplicationUserController {

    @Autowired
    ApplicationUserService applicationUserService;

    @GetMapping("/{id}")
    public ApplicationuserJpa getUserById(@PathVariable("id") int id) {
        var user = applicationUserService.getUserById(id);
        return user.orElse(null);
    }

    @PostMapping("")
    public ApplicationuserJpa createUser(@RequestBody ApplicationuserJpa user) throws AppException {
        try {
            applicationUserService.createUser(user);
        } catch (Exception e) {
            throw new AppException(HttpStatus.BAD_REQUEST, AppExceptionCode.USER_NOT_FOUND, "User already exists");
        }
        return user;
    }

    @GetMapping("/getusername/{username}")
    public ApplicationuserJpa getByUsername(@PathVariable("username") String username){
        var user = applicationUserService.getUserByUsername(username);
        return user.orElse(null);
    }

    @GetMapping("/{username}/isRegistered")
    public Boolean isRegistered(@PathVariable("username") String username){
        var user = applicationUserService.getUserByUsername(username);
        return user.isPresent();
    }

    @GetMapping("/")
    public String hello() {
        System.out.println("IN GET USER");
        return "hello";
    }
}
