package efrei.carrental.application.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto {

    private int id;

    private ApplicationUserDto user;

    private List<RentalDto> rentals = new ArrayList<>();

    private Boolean isValidated = false;
}
