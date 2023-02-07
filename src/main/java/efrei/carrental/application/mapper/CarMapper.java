package efrei.carrental.application.mapper;

import efrei.carrental.application.dto.CarDto;
import efrei.carrental.external.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    CarDto toDTO(Car car);

    Car fromDTO(CarDto carDto);
}