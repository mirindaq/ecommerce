package com.ecommerce.exception.handle;

import com.ecommerce.exception.custom.EmailAlreadyExistsException;
import com.ecommerce.exception.custom.LoginException;
import com.ecommerce.model.response.ErrorRegisterResponse;
import com.ecommerce.model.response.Response;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Response<ErrorRegisterResponse>> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        ErrorRegisterResponse errorDetail  = new ErrorRegisterResponse(ex.getMessage());
        Response<ErrorRegisterResponse> response = new Response<>(
                "Lỗi",
                errorDetail
        );
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<Response<String>> handleLoginException(LoginException ex) {
        Response<String> response = new Response<>(
                "Lỗi",
                ex.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

//    @ExceptionHandler(ExpiredJwtException.class)
//    public ResponseEntity<Object> handleExpiredJwtException(ExpiredJwtException ex) {
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token expired");
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Object> handleOtherExceptions(Exception ex) {
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
//    }

}
