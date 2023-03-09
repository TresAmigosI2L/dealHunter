package fr.ulco.dealhunter.exceptions;

import static java.lang.String.format;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String username) {
        super(format("User with username: '%s' already exists", username));
    }
}
