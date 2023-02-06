package efrei.carrental.service;

import efrei.carrental.commons.AppExceptionCode;
import efrei.carrental.exceptions.AppException;
import efrei.carrental.model.jpa.Rental;
import efrei.carrental.model.jpa.Reservation;
import efrei.carrental.model.repo.CarRepository;
import efrei.carrental.model.repo.ReservationRepository;
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
