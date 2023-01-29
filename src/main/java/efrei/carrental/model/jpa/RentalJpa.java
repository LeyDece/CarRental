package efrei.carrental.model.jpa;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

@Data
@Entity
@Table(name = "rental", schema = "public", catalog = "postgres")
public class RentalJpa {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "customer", nullable = false)
    private int customer;
    @Basic
    @Column(name = "car", nullable = false)
    private int car;
    @Basic
    @Column(name = "start_date", nullable = false)
    private Date startDate;
    @Basic
    @Column(name = "end_date", nullable = false)
    private Date endDate;
    @Basic
    @Column(name = "total_fee", nullable = false, precision = 2)
    private BigDecimal totalFee;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentalJpa rentalJpa = (RentalJpa) o;
        return id == rentalJpa.id && customer == rentalJpa.customer && car == rentalJpa.car && Objects.equals(startDate, rentalJpa.startDate) && Objects.equals(endDate, rentalJpa.endDate) && Objects.equals(totalFee, rentalJpa.totalFee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, car, startDate, endDate, totalFee);
    }
}
