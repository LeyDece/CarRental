package efrei.carrental.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Date;

@Data
public class RentalDto {

    @NotNull
    public int carId;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;

    @NotNull
    private BigDecimal price;

}
