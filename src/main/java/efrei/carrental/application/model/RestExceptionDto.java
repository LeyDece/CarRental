package efrei.carrental.application.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestExceptionDto {
    public int status;
    public String error;
    public String message;
    public long timestamp;

}

