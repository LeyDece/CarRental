package efrei.carrental.application.model;

import efrei.carrental.application.dto.RentalDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalRequestBodyDto {

    @NotNull
    private int customerId;

    @NotNull
    private RentalDto rentalDto;
}
