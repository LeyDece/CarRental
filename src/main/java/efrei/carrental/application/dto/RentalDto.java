package efrei.carrental.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
