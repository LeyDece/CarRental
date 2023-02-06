package efrei.carrental.controller;

import efrei.carrental.model.dto.ReportDto;
import efrei.carrental.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/reservation")
public class ReservationController {

    RentalService rentalService;

    @Autowired
    public ReservationController(RentalService rentalService){
        this.rentalService = rentalService;
    }

    @GetMapping("/report")
    public ResponseEntity<ReportDto> getReport() {
        var report = rentalService.getReservationAndAvailabilityReport();
        return new ResponseEntity(report, HttpStatus.OK);
    }
}
