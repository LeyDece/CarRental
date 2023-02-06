package efrei.carrental.model.dto;


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
