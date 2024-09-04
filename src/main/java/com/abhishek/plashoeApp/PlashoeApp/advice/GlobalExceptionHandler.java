package com.abhishek.plashoeApp.PlashoeApp.advice;

import com.razorpay.RazorpayException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiResponse<?>> handleNoSuchElementException(NoSuchElementException exception){
        ApiError apiError = new ApiError(exception.getMessage(), HttpStatus.NOT_FOUND);
        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(RazorpayException.class)
    public ResponseEntity<ApiResponse<?>> handleRazorpayException(RazorpayException exception){
        ApiError apiError = new ApiError(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResponse<?>> handleAuthenticationException(AuthenticationException exception){

        ApiError apiError = new ApiError(exception.getMessage(), HttpStatus.UNAUTHORIZED);
        return buildErrorResponseEntity(apiError);

    }
    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiResponse<?>> handleAuthenticationException(JwtException exception){

        ApiError apiError = new ApiError(exception.getMessage(), HttpStatus.UNAUTHORIZED);
        return buildErrorResponseEntity(apiError);

    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse<?>> handleBadCredentialsException(BadCredentialsException exception){
        ApiError apiError = new ApiError(exception.getMessage(), HttpStatus.BAD_REQUEST);
      return buildErrorResponseEntity(apiError);
    }



    private ResponseEntity<ApiResponse<?>> buildErrorResponseEntity(ApiError apiError){
        return new ResponseEntity<>(new ApiResponse<>(apiError),apiError.getStatusCode());
    }

}
