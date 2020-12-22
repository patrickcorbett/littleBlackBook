package com.pcorbett.littleBlackBook.controllers.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.pcorbett.littleBlackBook.exceptions.UserNotFoundException;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public @ResponseStatus(code = HttpStatus.NOT_FOUND) void handleUserNotFoundException(
			UserNotFoundException pUserNotFoundException) {

	}

//	@ExceptionHandler(ConstraintViolationException.class)
//	public void handleConstraintViolationException(ConstraintViolationException pConstraintViolationException,
//			HttpServletResponse response) throws IOException {
//		response.sendError(HttpStatus.BAD_REQUEST.value(), pConstraintViolationException.getMessage());
//	}

}
