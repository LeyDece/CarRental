package efrei.carrental.model.jpa;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "reservations", schema = "public", catalog = "postgres")
@Entity
public class Reservation {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne()
    @JoinColumn(name = "applicationuser_id", referencedColumnName = "id")
    private Applicationuser user;

    @ElementCollection
    @CollectionTable(
            name = "rental_reservation",
            joinColumns=@JoinColumn(name = "reservation_id", referencedColumnName = "id"
    ))
    private List<Rental> rentals = new ArrayList<>();

    @Column(name = "validation", nullable = false)
    private Boolean isValidated = false;
}
