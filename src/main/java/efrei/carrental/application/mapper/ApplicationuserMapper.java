package efrei.carrental.application.mapper;

import efrei.carrental.application.dto.ApplicationuserDto;
import efrei.carrental.external.entity.Applicationuser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ApplicationuserMapper {

    ApplicationuserMapper INSTANCE = Mappers.getMapper(ApplicationuserMapper.class);

    ApplicationuserDto toDTO(Applicationuser applicationuser);

    Applicationuser fromDTO(ApplicationuserDto applicationuserDto);
}
