package efrei.carrental.application.exceptions;

import org.springframework.http.HttpStatus;

public enum AppExceptionCode {
    USER_NOT_FOUND,
    CAR_NOT_FOUND,
    CAR_NOT_IN_STOCK,
    ACCESSDENIED, CAR_ALREADY_RENT, RESERVATION_SAVE_ISSUE, RESERVATION_NOT_FOUND
}