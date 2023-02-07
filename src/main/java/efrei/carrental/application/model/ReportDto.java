package efrei.carrental.application.model;


import efrei.carrental.application.dto.CarDto;
import efrei.carrental.application.dto.ReservationDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDto {

    private Map<Integer, List<ReservationDto>> reservations;
    private List<CarDto> availableCars;

}
