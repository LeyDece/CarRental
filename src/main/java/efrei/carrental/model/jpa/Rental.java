package efrei.carrental.model.jpa;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

@Embeddable
@Data
public class Rental {

    @ManyToOne( cascade = CascadeType.ALL )
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    public Car car;
    @NotNull
    public Date startDate;

    @NotNull
    public Date endDate;

    @NotNull
    public BigDecimal price;
    @Override
    public int hashCode() {
        return Objects.hash(car, startDate, endDate, price);
    }
}
