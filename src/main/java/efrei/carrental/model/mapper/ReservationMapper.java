package efrei.carrental.model.mapper;

import efrei.carrental.model.dto.ReservationDto;
import efrei.carrental.model.jpa.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReservationMapper {

    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);

    ReservationDto toDTO(Reservation reservation);

    Reservation fromDTO(ReservationDto reservationDto);

}
