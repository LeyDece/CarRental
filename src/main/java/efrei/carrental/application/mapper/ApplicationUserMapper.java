package efrei.carrental.application.mapper;

import efrei.carrental.application.dto.ApplicationUserDto;
import efrei.carrental.external.entity.ApplicationUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ApplicationUserMapper {

    ApplicationUserMapper INSTANCE = Mappers.getMapper(ApplicationUserMapper.class);

    ApplicationUserDto toDTO(ApplicationUser applicationuser);

    ApplicationUser fromDTO(ApplicationUserDto applicationuserDto);
}
