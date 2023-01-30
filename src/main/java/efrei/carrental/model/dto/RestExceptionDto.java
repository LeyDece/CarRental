package efrei.carrental.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestExceptionDto {
    public int status;
    public String error;
    public String message;
    public long timestamp;

    public RestExceptionDto() {

    }

    public RestExceptionDto(int status, String error, String message, int timestamp) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.timestamp = timestamp;
    }


}

