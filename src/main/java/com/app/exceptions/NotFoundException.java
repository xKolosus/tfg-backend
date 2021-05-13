package com.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "item_not_found")
public class NotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public NotFoundException(String message) {
		super(message);
	}
	
	public NotFoundException() {
		super("Error, not found!");
	}
}
