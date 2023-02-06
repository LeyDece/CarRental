package efrei.carrental.model.mapper;

import efrei.carrental.model.dto.ApplicationuserDto;
import efrei.carrental.model.jpa.Applicationuser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ApplicationuserMapper {

    ApplicationuserMapper INSTANCE = Mappers.getMapper(ApplicationuserMapper.class);

    ApplicationuserDto toDTO(Applicationuser applicationuser);

    Applicationuser fromDTO(ApplicationuserDto applicationuserDto);
}
