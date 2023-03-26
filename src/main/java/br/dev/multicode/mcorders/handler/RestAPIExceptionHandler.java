package br.dev.multicode.mcorders.handler;

import br.dev.multicode.mcorders.handler.exceptions.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class RestAPIExceptionHandler extends ResponseEntityExceptionHandler {


  @ExceptionHandler(NotFoundException.class)
  protected ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
    log.error(ex.getMessage(), ex);
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(new ApiError(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    List<ApiError> apiErrors = new ArrayList<>();
    ex.getBindingResult()
        .getFieldErrors()
        .forEach(fieldError -> apiErrors.add(new ApiError(HttpStatus.BAD_REQUEST.value(),
            String.format("'%s' %s", fieldError.getField(), fieldError.getDefaultMessage()))));

    if (apiErrors.isEmpty()) {
      apiErrors.add(new ApiError(HttpStatus.valueOf(status.value()).value(), "Oops!"));
    }

    log.error(ex.getMessage(), ex);
    return ResponseEntity.status(status).body(apiErrors);
  }

}
