package com.nickbern.vetturno.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import javax.naming.AuthenticationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> manejarValidacion(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();
        for (FieldError fe : ex.getBindingResult().getFieldErrors()) {
            errores.put(fe.getField(), fe.getDefaultMessage());
        }
        ApiError cuerpo = new ApiError(HttpStatus.BAD_REQUEST.value(),
                "Los datos enviados no son válidos", errores);
        return ResponseEntity.badRequest().body(cuerpo);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<ApiError> manejarNegocio(NegocioException ex) {
        ApiError cuerpo = new ApiError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), null);
        return ResponseEntity.badRequest().body(cuerpo);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> manejarGeneral(Exception ex) {
        log.error("Error inesperado", ex);
        ApiError cuerpo = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Ocurrió un error inesperado en el servidor", null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(cuerpo);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiError> manejarAutenticacion(AuthenticationException ex) {
        ApiError cuerpo = new ApiError(HttpStatus.UNAUTHORIZED.value(), "Credenciales inválidas", null);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(cuerpo);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> manejarJsonInvalido(HttpMessageNotReadableException ex) {
        ApiError cuerpo = new ApiError(HttpStatus.BAD_REQUEST.value(),
                "El cuerpo de la petición no es válido. Revisa el formato JSON y las fechas (yyyy-MM-ddTHH:mm:ss)", null);
        return ResponseEntity.badRequest().body(cuerpo);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiError> manejarTipoInvalido(MethodArgumentTypeMismatchException ex) {
        ApiError cuerpo = new ApiError(HttpStatus.BAD_REQUEST.value(),
                "El parámetro '" + ex.getName() + "' tiene un valor inválido", null);
        return ResponseEntity.badRequest().body(cuerpo);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiError> manejarRutaInexistente(NoResourceFoundException ex) {
        ApiError cuerpo = new ApiError(HttpStatus.NOT_FOUND.value(), "Recurso no encontrado", null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cuerpo);
    }

}