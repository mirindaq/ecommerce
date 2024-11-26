//package com.ecommerce.exception.handle;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import com.ecommerce.exception.custom.StorageException;
//import com.ecommerce.exception.custom.StorageFileNotFoundException;
//import com.ecommerce.model.dto.ErrorResponseDTO;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//
//@ControllerAdvice
//public class ControllerAdvisor extends ResponseEntityExceptionHandler {
//    @ExceptionHandler(ArithmeticException.class)
//    public ResponseEntity<Object> handleArithmeticException(ArithmeticException ex, WebRequest request) {
//        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
//        errorResponseDTO.setError(ex.getMessage());
//        List<String> details = new ArrayList<String>();
//        details.add("Khong co phep chia cho 0");
//        errorResponseDTO.setDetails(details);
//        return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @ExceptionHandler(StorageException.class)
//    public ResponseEntity<Object> handleStorageException(StorageException ex, WebRequest request) {
//        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
//        errorResponseDTO.setError(ex.getMessage());
//        List<String> details = new ArrayList<String>();
//        details.add(request.toString());
//        errorResponseDTO.setDetails(details);
//        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(StorageException.class)
//    public ResponseEntity<Object> handleStorageFileNotFoundExceptiion(StorageFileNotFoundException ex, WebRequest request) {
//        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
//        errorResponseDTO.setError(ex.getMessage());
//        List<String> details = new ArrayList<String>();
//        details.add(request.toString());
//        errorResponseDTO.setDetails(details);
//        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
//    }
//
//}