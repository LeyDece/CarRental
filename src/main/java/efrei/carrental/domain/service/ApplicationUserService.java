package efrei.carrental.domain.service;


import efrei.carrental.application.exceptions.AppExceptionCode;
import efrei.carrental.application.exceptions.AppException;
import efrei.carrental.application.dto.RentalDto;
import efrei.carrental.application.security.AppRole;
import efrei.carrental.external.entity.ApplicationUser;
import efrei.carrental.external.entity.Reservation;
import efrei.carrental.application.mapper.RentalMapper;
import efrei.carrental.external.repository.ApplicationUserRepository;
import efrei.carrental.external.repository.ReservationRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ApplicationUserService {

    private final ApplicationUserRepository userRepository;

    private final CatalogService catalogService;

    private final ReservationRepository reservationRepository;

    RentalMapper rentalMapper = Mappers.getMapper(RentalMapper.class);

    private JavaMailSender mailSender;

    private PasswordEncoder passwordEncoder;
    private Environment env;

    @Autowired
    public ApplicationUserService(ApplicationUserRepository userRepository, CatalogService catalogService, ReservationRepository reservationRepository, JavaMailSender mailSender, Environment env, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.catalogService = catalogService;
        this.reservationRepository = reservationRepository;
        this.mailSender = mailSender;
        this.env = env;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<ApplicationUser> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public ApplicationUser createUser(ApplicationUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }

    public Optional<ApplicationUser> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void addRentalToCart(RentalDto rentalDto, int customerId) {
        var user = userRepository.findById(customerId).orElseThrow(() -> new AppException(HttpStatus.BAD_REQUEST, AppExceptionCode.CAR_NOT_FOUND, "Cannot rent a non existing car"));
        var car = catalogService.getCarById(rentalDto.getCarId()).orElseThrow(() -> new AppException(HttpStatus.BAD_REQUEST, AppExceptionCode.USER_NOT_FOUND, "Cannot add rentalDto to non existing user"));

        if(!car.isAvailability()) throw new AppException(HttpStatus.BAD_REQUEST, AppExceptionCode.CAR_NOT_IN_STOCK, "Car not in stock");

        var rental = rentalMapper.fromDTO(rentalDto);
        rental.setCar(car);
        user.getCart().add(rental);
        userRepository.save(user);
    }

    public void clearCart(int customerId) {
        var user = userRepository.findById(customerId).orElseThrow(() -> new AppException(HttpStatus.BAD_REQUEST, AppExceptionCode.USER_NOT_FOUND, "Cannot clear non existing user cart"));

        user.getCart().clear();
        userRepository.save(user);
    }

    public List<RentalDto> getCartContent(int customerId) {
        var user = userRepository.findById(customerId).orElseThrow(() -> new AppException(HttpStatus.BAD_REQUEST, AppExceptionCode.USER_NOT_FOUND, "Cannot get cart of non existing user"));
        return user.getCart().stream().map(e -> rentalMapper.toDTO(e)).toList();
    }

    public void submitCart(int customerId) {
        var user = userRepository.findById(customerId).orElseThrow(() -> new AppException(HttpStatus.BAD_REQUEST, AppExceptionCode.USER_NOT_FOUND, "Cannot get cart of non existing user"));

        Reservation reservation = createReservationAndClearCart(user);
        var newReservation = reservationRepository.save(reservation);
        confirmReservation(env.getProperty("spring.mail.username"), user.getEmail(), newReservation.getId(), user.getId());
    }

    private Reservation createReservationAndClearCart(ApplicationUser user) {
        Reservation reservation = new Reservation();
        reservation.getRentals().addAll(user.getCart());
        reservation.setUser(user);

        clearCart(user.id);

        return reservation;
    }

    private void confirmReservation(String emailFrom, String emailTo, int reservationId, int userId){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailFrom);
        message.setTo(emailTo);
        message.setSubject("Confirmation de la réservation");
        message.setText("Lien pour valider la réservation : http://localhost:8080/rest/reservation/valid/" + reservationId + "/"+ userId +"");

        mailSender.send(message);
    }

}
