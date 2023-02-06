package efrei.carrental.service;


import efrei.carrental.commons.AppExceptionCode;
import efrei.carrental.exceptions.AppException;
import efrei.carrental.model.RentalMapper;
import efrei.carrental.model.dto.RentalDto;
import efrei.carrental.model.jpa.Applicationuser;
import efrei.carrental.model.jpa.Rental;
import efrei.carrental.model.jpa.Reservation;
import efrei.carrental.model.repo.ApplicationUserRepository;
import efrei.carrental.model.repo.ReservationRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ApplicationUserService {

    private final ApplicationUserRepository userRepository;

    private final CatalogService catalogService;

    private final ReservationRepository reservationRepository;

    RentalMapper rentalMapper = Mappers.getMapper(RentalMapper.class);

    @Autowired
    public ApplicationUserService(ApplicationUserRepository userRepository, CatalogService catalogService, ReservationRepository reservationRepository) {
        this.userRepository = userRepository;
        this.catalogService = catalogService;
        this.reservationRepository = reservationRepository;
    }

    public Optional<Applicationuser> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public Applicationuser createUser(Applicationuser user) {
        userRepository.save(user);
        return user;
    }

    public Optional<Applicationuser> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    public void addRentalToCart(RentalDto rentalDto, int customerId) {
        var user = userRepository.findById(customerId);
        var car = catalogService.getCarById(rentalDto.getCarId());

        if (car.isEmpty())
            throw new AppException(HttpStatus.BAD_REQUEST, AppExceptionCode.CAR_NOT_FOUND, "Cannot rent a non existing car");
        if (user.isEmpty())
            throw new AppException(HttpStatus.BAD_REQUEST, AppExceptionCode.USER_NOT_FOUND, "Cannot add rentalDto to non existing user");

        var rental = rentalMapper.fromDTO(rentalDto);
        rental.setCar(car.get());
        user.get().getCart().add(rental);
        userRepository.save(user.get());
    }

    public void clearCart(int customerId) {
        var user = userRepository.findById(customerId);
        if (user.isEmpty())
            throw new AppException(HttpStatus.BAD_REQUEST, AppExceptionCode.USER_NOT_FOUND, "Cannot clear non existing user cart");

        user.get().getCart().clear();
        userRepository.save(user.get());
    }

    public List<RentalDto> getCartContent(int customerId) {
        var user = userRepository.findById(customerId);
        if (user.isEmpty())
            throw new AppException(HttpStatus.BAD_REQUEST, AppExceptionCode.USER_NOT_FOUND, "Cannot get cart of non existing user");

        return user.get().getCart().stream().map(e -> rentalMapper.toDTO(e)).toList();
    }

    public void submitCart(int customerId){
        var user = userRepository.findById(customerId).orElseThrow(()-> new AppException(HttpStatus.BAD_REQUEST, AppExceptionCode.USER_NOT_FOUND, "Cannot get cart of non existing user"));

        //@TODO Verify car stock

        Reservation reservation = new Reservation();
        reservation.getRentals().addAll(user.getCart());
        reservation.setUser(user);
        reservationRepository.save(reservation);
    }

}
