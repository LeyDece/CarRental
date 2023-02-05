package efrei.carrental.service;

import efrei.carrental.commons.AppExceptionCode;
import efrei.carrental.exceptions.AppException;
import efrei.carrental.model.RentalMapper;
import efrei.carrental.model.dto.RentalDto;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RentalService {

    @Autowired
    ApplicationUserService applicationUserService;

    @Autowired
    CatalogService catalogService;

    RentalMapper rentalMapper = Mappers.getMapper(RentalMapper.class);

    public void addRentalToCart(RentalDto rentalDto, int customerId) {
        var user = applicationUserService.getUserById(customerId);
        var car = catalogService.getCarById(rentalDto.getCarId());

        if (car.isEmpty())
            throw new AppException(HttpStatus.BAD_REQUEST, AppExceptionCode.CAR_NOT_FOUND, "Cannot rent a non existing car");
        if (user.isEmpty())
            throw new AppException(HttpStatus.BAD_REQUEST, AppExceptionCode.USER_NOT_FOUND, "Cannot add rentalDto to non existing user");

        var rental = rentalMapper.fromDTO(rentalDto);
        rental.setCar(car.get());
        user.get().getCart().add(rental);
        applicationUserService.createUser(user.get());

    }

}
