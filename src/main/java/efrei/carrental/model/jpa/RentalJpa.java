package efrei.carrental.model.jpa;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

@Embeddable
public class RentalJpa {

    @ManyToOne( cascade = CascadeType.ALL )
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private CarJpa car;
    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;

    @NotNull
    private BigDecimal price;

    @Override
    public int hashCode() {
        return Objects.hash(car, startDate, endDate, price);
    }
}
