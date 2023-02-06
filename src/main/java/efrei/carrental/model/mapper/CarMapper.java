package efrei.carrental.model.mapper;

import efrei.carrental.model.dto.CarDto;
import efrei.carrental.model.jpa.Car;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    CarDto toDTO(Car car);

    Car fromDTO(CarDto carDto);
}