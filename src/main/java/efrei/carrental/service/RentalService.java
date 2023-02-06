package efrei.carrental.service;

import efrei.carrental.model.dto.ReportDto;
import efrei.carrental.model.mapper.CarMapper;
import efrei.carrental.model.mapper.ReservationMapper;
import efrei.carrental.model.repo.ApplicationUserRepository;
import efrei.carrental.model.repo.CarRepository;
import efrei.carrental.model.repo.ReservationRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Transactional
public class RentalService {

    private final CatalogService catalogService;
    private final ReservationRepository reservationRepository;

    ReservationMapper reservationMapper = Mappers.getMapper(ReservationMapper.class);
    CarMapper carMapper = Mappers.getMapper(CarMapper.class);


    @Autowired
    public RentalService(CatalogService catalogService, ReservationRepository reservationRepository) {
        this.catalogService = catalogService;
        this.reservationRepository = reservationRepository;
    }

    public ReportDto getReservationAndAvailabilityReport() {

        ReportDto report = new ReportDto();

        var reservation = reservationRepository.
                findAll()
                .stream()
                .map(e -> reservationMapper.toDTO(e)).toList();

        var reservationPerId = reservation.stream()
                .map(e -> new ImmutablePair<>(e.getUser().getId(), e)).toList();

        var reservationMap = reservationPerId.stream().collect(Collectors.groupingBy(Pair::getKey, Collectors.mapping(Pair::getValue, Collectors.toList())));
        var availableCars = catalogService.getAllOffers().stream().map(e -> carMapper.toDTO(e)).toList();

        report.setReservations(reservationMap);
        report.setAvailableCars(availableCars);
        return report;
    }


}
