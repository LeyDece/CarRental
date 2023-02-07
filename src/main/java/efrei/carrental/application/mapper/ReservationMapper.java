package efrei.carrental.application.mapper;

import efrei.carrental.application.dto.ReservationDto;
import efrei.carrental.external.entity.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReservationMapper {

    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);

    ReservationDto toDTO(Reservation reservation);

    Reservation fromDTO(ReservationDto reservationDto);

}
