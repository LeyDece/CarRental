package efrei.carrental.model.jpa;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Data
@Entity
@Table(name = "car", schema = "public", catalog = "postgres", uniqueConstraints = @UniqueConstraint(columnNames = {"brand", "year", "model"}))
public class Car {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    public int id;

    @Basic
    @Column(name = "model", nullable = false, length = 255)
    public String model;

    @Basic
    @Column(name = "brand", nullable = false, length = 255)
    public String brand;
    @Basic
    @Column(name = "year", nullable = false)
    public int year;
    @Basic
    @Column(name = "rental_fee", nullable = false, precision = 6, scale = 2)
    public BigDecimal rentalFee;
    @Basic
    @Column(name = "availability", nullable = false)
    public boolean availability;

    @Basic
    @Column(name = "offer_fee", precision = 6, scale = 2)
    public BigDecimal offerFee;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id && year == car.year && availability == car.availability && Objects.equals(model, car.model) && Objects.equals(brand, car.brand) && Objects.equals(rentalFee, car.rentalFee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, brand, year, rentalFee, availability);
    }
}
