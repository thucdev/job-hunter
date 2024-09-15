package vn.hoidanit.jobhunter.service.error;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import vn.hoidanit.jobhunter.domain.RestResponse;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(value = { UsernameNotFoundException.class, BadCredentialsException.class })
    public ResponseEntity<String> handleException(Exception e) {
        RestResponse<Object> res = new RestResponse<Object>();

        res.setStatusCode(HttpStatus.BAD_REQUEST.value());
        res.setError(e.getMessage());
        res.setMessage("InValidException");
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestResponse<Object>> validationError(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();
        RestResponse<Object> res = new RestResponse<Object>();
        res.setStatusCode(HttpStatus.BAD_REQUEST.value());
        res.setError(e.getBody().getDetail());

        List<String> errors = fieldErrors.stream().map(f -> f.getDefaultMessage()).collect(Collectors.toList());
        res.setMessage(errors.size() > 1 ? errors : errors.get(0));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }
}
