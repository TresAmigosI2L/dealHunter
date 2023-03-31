package fr.ulco.dealhunter.exceptions;

import java.util.UUID;

import static java.lang.String.format;

public class CommentNotFoundException extends RuntimeException {
    public CommentNotFoundException(UUID id) {
        super(format("Comment with id: '%s' not found", id));
    }
}
