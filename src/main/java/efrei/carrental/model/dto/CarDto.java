package efrei.carrental.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
public class CarDto {

    private int id;

    private String model;

    private String brand;

    private Integer year;

    private BigDecimal rentalFee;

    private boolean availability;

    private BigDecimal offerFee;

}
