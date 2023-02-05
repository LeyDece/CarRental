package efrei.carrental.model.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class RentalRequestBodyDto {

    @NotNull
    private int customerId;

    @NotNull
    private RentalDto rentalDto;
}
