package efrei.carrental.application.mapper;


import efrei.carrental.application.dto.RentalDto;
import efrei.carrental.external.entity.Rental;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RentalMapper {

    RentalMapper INSTANCE = Mappers.getMapper(RentalMapper.class);

    @Mapping(target="carId", source="car.id")
    RentalDto toDTO(Rental rental);

    Rental fromDTO(RentalDto rental);

}
