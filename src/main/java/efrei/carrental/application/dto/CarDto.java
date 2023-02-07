package efrei.carrental.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {

    private int id;

    private String model;

    private String brand;

    private Integer year;

    private BigDecimal rentalFee;

    private boolean availability;

    private BigDecimal offerFee;

}
