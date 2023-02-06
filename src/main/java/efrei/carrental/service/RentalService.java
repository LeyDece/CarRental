package efrei.carrental.service;

import efrei.carrental.commons.AppExceptionCode;
import efrei.carrental.exceptions.AppException;
import efrei.carrental.model.RentalMapper;
import efrei.carrental.model.dto.RentalDto;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RentalService {

    @Autowired
    ApplicationUserService applicationUserService;



}
