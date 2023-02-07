package efrei.carrental.application.rest.controller;

import efrei.carrental.application.model.ReportDto;
import efrei.carrental.application.exceptions.AppExceptionCode;
import efrei.carrental.application.exceptions.AppException;
import efrei.carrental.domain.service.RentalService;
import efrei.carrental.domain.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/rest/reservation")
public class ReservationController {

    private RentalService rentalService;

    private ReservationService reservationService;
    private Environment env;
    @Autowired
    public ReservationController(RentalService rentalService, ReservationService reservationService){
        this.rentalService = rentalService;
        this.reservationService = reservationService;
    }

    @GetMapping("/report")
    public ResponseEntity<ReportDto> getReport() {
        var report = rentalService.getReservationAndAvailabilityReport();
        return new ResponseEntity(report, HttpStatus.OK);
    }

    @GetMapping("/valid/{id}/{idUser}")
    @ResponseBody
    public String validReservation(@PathVariable("id") int reservationId,@PathVariable("idUser") int idUser){
        var reservation = reservationService.getReservationById(reservationId).orElseThrow(() -> new AppException(HttpStatus.NOT_MODIFIED, AppExceptionCode.RESERVATION_NOT_FOUND,"Reservation non trouvée"));
        if(reservation.getUser().getId() != idUser){
            throw new AppException(HttpStatus.FORBIDDEN, AppExceptionCode.ACCESSDENIED, "Réservation ne vous appartient pas");
        }
        reservation.setIsValidated(true);
        var newRes =  reservationService.save(reservation);
        if(newRes == null){
            throw new  AppException(HttpStatus.BAD_REQUEST,AppExceptionCode.RESERVATION_SAVE_ISSUE,"Problème de sauvegarde de la réservation");
        }
        reservationService.validateReservation(newRes);
        return "Validation réussie";
    }

}
