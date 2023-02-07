package efrei.carrental.application.rest.controller;

import efrei.carrental.application.dto.ApplicationUserCreatorDto;
import efrei.carrental.application.dto.ApplicationUserDto;
import efrei.carrental.application.dto.RentalDto;
import efrei.carrental.application.model.RentalRequestBodyDto;
import efrei.carrental.application.exceptions.AppExceptionCode;
import efrei.carrental.application.exceptions.AppException;
import efrei.carrental.external.entity.ApplicationUser;
import efrei.carrental.domain.service.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.session.SessionRepository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/rest/users")
public class ApplicationUserController {

    @Autowired
    ApplicationUserService applicationUserService;

    @GetMapping("/{id}")
    public ApplicationUser getUserById(@PathVariable("id") int id) {
        var user = applicationUserService.getUserById(id);
        return user.orElse(null);
    }

    @GetMapping("/me")
    @PreAuthorize("hasAnyAuthority('ROLE_CUSTOMER')")
    public ApplicationUser getInfos(Authentication authentication) {
        if(authentication.isAuthenticated()){
            var user = applicationUserService.getUserByUsername(authentication.getName()).get();
            return user;
        }
        throw new AppException(HttpStatus.BAD_REQUEST, AppExceptionCode.ACCESSDENIED, "User not authentified");
    }

    @PostMapping("")
    public ApplicationUser createUser(@RequestBody @Valid ApplicationUserCreatorDto user) throws AppException {
        ApplicationUser appUser = null;
        try {
            appUser = applicationUserService.createUser(DtoCreatorToAppUser(user));
        } catch (Exception e) {
            throw new AppException(HttpStatus.BAD_REQUEST, AppExceptionCode.USER_NOT_FOUND, "User already exists");
        }
        return appUser;
    }

    @GetMapping("/getusername/{username}")
    public ApplicationUser getByUsername(@PathVariable("username") String username) {
        var user = applicationUserService.getUserByUsername(username);
        return user.orElse(null);
    }

    @GetMapping("/{username}/isRegistered")
    @PreAuthorize("hasAnyAuthority('ROLE_AGENT')")
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

    private ApplicationUser DtoCreatorToAppUser(ApplicationUserCreatorDto applicationUserCreatorDto){
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setUsername(applicationUserCreatorDto.getUsername());
        applicationUser.setPassword(applicationUserCreatorDto.getPassword());
        applicationUser.setEmail(applicationUserCreatorDto.getEmail());
        applicationUser.setUserType(applicationUserCreatorDto.getUserType());
        return applicationUser;
    }

}
