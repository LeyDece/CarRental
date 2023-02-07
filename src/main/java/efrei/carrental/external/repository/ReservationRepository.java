package efrei.carrental.external.repository;

import efrei.carrental.external.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    public Optional<Reservation> findById(Integer id);

    public Optional<Reservation> findByUserId(Integer userId);

    public List<Reservation> findAll();
}