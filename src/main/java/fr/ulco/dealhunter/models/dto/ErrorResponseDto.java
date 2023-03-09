package fr.ulco.dealhunter.models.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ErrorResponseDto implements Serializable {
    private final String message;
}
