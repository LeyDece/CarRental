package efrei.carrental.exceptions;

import efrei.carrental.commons.AppExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Class générique qui permet de représenter les erreurs retournées par l'appel à une API Rest
 */
@Data
@AllArgsConstructor
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AppException extends RuntimeException {
    private static final long serialVersionUID = -2206309364141569L;

    private final HttpStatus status;
    private final AppExceptionCode code;
    private final String message;

}