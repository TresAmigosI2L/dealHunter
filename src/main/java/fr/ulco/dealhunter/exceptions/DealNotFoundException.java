package fr.ulco.dealhunter.exceptions;

import java.util.UUID;
import static java.lang.String.format;

public class DealNotFoundException extends RuntimeException {
    public DealNotFoundException(UUID id) {
        super(format("Deal with id: '%s' not found", id));
    }
}
