package com.ecommerce.exception.handle;

import com.ecommerce.model.response.ErrorRegisterResponse;
import com.ecommerce.model.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Response<ErrorRegisterResponse>> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        ErrorRegisterResponse errorDetail  = new ErrorRegisterResponse("Tài khoản đã tồn tại");
        Response<ErrorRegisterResponse> response = new Response<>(
                "Lỗi",
                errorDetail
        );

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
}
