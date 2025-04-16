package com.ecommerce.product_catalog_service.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.ecommerce.product_catalog_service.dto.errors.ErrorDtoNotFound;
import com.ecommerce.product_catalog_service.dto.errors.ErrorDtoResponse;
import com.ecommerce.product_catalog_service.dto.errors.ErrorGenericDto;

@ControllerAdvice
public class GlobalExceptionHandler {
   public GlobalExceptionHandler() {
   }

   @ExceptionHandler({MethodArgumentNotValidException.class})
   public ResponseEntity<ErrorGenericDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
      List<Map<String, String>> errorList = ex.getFieldErrors().stream().map((fieldError) -> {
         Map<String, String> errorMap = new HashMap<>();
         errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
         return errorMap;
      }).toList();
      ErrorGenericDto errorsDto = new ErrorGenericDto("One or more fields are incorrect.", errorList);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorsDto);
   }

   @ExceptionHandler({ResourceNotFoundException.class})
   public ResponseEntity<ErrorDtoNotFound> handleNotFoundException(ResourceNotFoundException ex, WebRequest webRequest) {
      ErrorDtoNotFound errorDtoNotFound = new ErrorDtoNotFound(webRequest.getDescription(false), ex.getMessage());
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDtoNotFound);
   }

   @ExceptionHandler({JwtException.class})
   public ResponseEntity<ErrorDtoResponse> handleJwtException(JwtException ex) {
      ErrorDtoResponse errorResponse = new ErrorDtoResponse("Token Error", ex.getMessage());
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
   }
}