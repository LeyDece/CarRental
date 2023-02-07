package efrei.carrental.domain.service;

import efrei.carrental.application.exceptions.AppExceptionCode;
import efrei.carrental.application.exceptions.AppException;
import efrei.carrental.external.entity.Rental;
import efrei.carrental.external.entity.Reservation;
import efrei.carrental.external.repository.CarRepository;
import efrei.carrental.external.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReservationService {

    private ReservationRepository reservationRepository;

    private CarRepository carRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, CarRepository carRepository){
        this.reservationRepository = reservationRepository;
        this.carRepository = carRepository;
    }
    public Optional<Reservation> getReservationById(int id){
        return reservationRepository.findByUserId(id);
    }

    public Reservation save(Reservation reservation){
        return reservationRepository.save(reservation);
    }

    public void validateReservation(Reservation reservation){
        List<Rental> rentals = reservation.getRentals();
        for(Rental r : rentals){
            if(!r.getCar().availability){
                throw new AppException(HttpStatus.NOT_MODIFIED, AppExceptionCode.CAR_ALREADY_RENT,"Voiture déjà occupée");
            }
            r.car.setAvailability(false);
            carRepository.save(r.car);
        }
    }

}
