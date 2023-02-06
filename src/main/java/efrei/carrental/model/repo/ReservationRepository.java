package efrei.carrental.model.repo;

import efrei.carrental.model.jpa.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
public Optional<Reservation> findById(Integer id);

public Optional<Reservation> findByUserId(Integer userId);

public List<Reservation> findAll();


        }