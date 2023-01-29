package efrei.carrental.model.jpa;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Data
@Entity
@Table(name = "car", schema = "public", catalog = "postgres")
public class CarJpa {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "model", nullable = false, length = 255)
    private String model;
    @Basic
    @Column(name = "brand", nullable = false, length = 255)
    private String brand;
    @Basic
    @Column(name = "year", nullable = false)
    private int year;
    @Basic
    @Column(name = "rental_fee", nullable = false, precision = 2)
    private BigDecimal rentalFee;
    @Basic
    @Column(name = "availability", nullable = false)
    private boolean availability;

    @Basic
    @Column(name = "offer_fee", precision = 2)
    private BigDecimal offerFee;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarJpa carJpa = (CarJpa) o;
        return id == carJpa.id && year == carJpa.year && availability == carJpa.availability && Objects.equals(model, carJpa.model) && Objects.equals(brand, carJpa.brand) && Objects.equals(rentalFee, carJpa.rentalFee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, brand, year, rentalFee, availability);
    }
}
