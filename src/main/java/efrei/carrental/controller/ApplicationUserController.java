package efrei.carrental.controller;

import efrei.carrental.model.jpa.ApplicationuserJpa;
import efrei.carrental.service.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/rest/users")
public class ApplicationUserController {

    @Autowired
    ApplicationUserService applicationUserService;

    @GetMapping("/{id}")
    public ApplicationuserJpa getUser(@PathVariable("id") int id){
        System.out.println("IN GET USER");
        var user = applicationUserService.getUser(id);
        return user.orElse(null);
    }

    @GetMapping("/")
    public String hello(){
        System.out.println("IN GET USER");
        return "hello";
    }
}
