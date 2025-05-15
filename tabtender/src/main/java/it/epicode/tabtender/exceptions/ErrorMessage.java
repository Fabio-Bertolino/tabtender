package it.epicode.tabtender.exceptions;


import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorMessage {
    private String message;
    private HttpStatus statusCode;
}