package efrei.carrental.application.rest.controller;

import efrei.carrental.application.exceptions.AppException;
import efrei.carrental.application.exceptions.AppExceptionCode;
import efrei.carrental.application.model.RestExceptionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

/**
 * Cette classe permet de gérer nos types d'exceptions comme {@link AppException},
 * {@link AccessDeniedException} et enfin les exceptions générales {@link Exception}
 *
 */
@RestControllerAdvice
public class AppControllerAdvice {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Permet de gérer les cas d'erreur applicative
     *
     * @param ex
     * @return La réponse à envoyer en cas de levé de cette erreur
     */
    @ExceptionHandler({AppException.class})
    public ResponseEntity<Object> handleAppException(AppException ex) {
        RestExceptionDto response = new RestExceptionDto();

        response.timestamp = Instant.now().toEpochMilli();
        response.status = ex.getStatus().value();
        response.message = ex.getMessage();
        response.error = ex.getCode().name();

        return ResponseEntity.status(ex.getStatus()).body(response);
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<Object> handleAppException(AccessDeniedException ex) {
        RestExceptionDto response = new RestExceptionDto();

        response.timestamp = Instant.now().toEpochMilli();
        response.status = HttpStatus.FORBIDDEN.value();
        response.message = ex.getMessage();
        response.error = AppExceptionCode.ACCESSDENIED.name();

        return ResponseEntity.status(response.status).body(response);
    }
}

