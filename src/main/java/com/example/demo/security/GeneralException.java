package com.example.demo.security;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class GeneralException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * A record class representing error details, including an HTTP status code and a message.
     */
    public record ErrorData(int status, String msg) {}

    private ErrorData errorData;

    /**
     * Constructs a GeneralException with the specified HTTP status and error message.
     *
     * @param status The HTTP status code associated with the exception.
     * @param msg    A descriptive message explaining the error.
     */
    public GeneralException(HttpStatus status, String msg) {
        this.setErrorData(new ErrorData( status.value()
                , msg));
    }

	public ErrorData getErrorData() {
		return errorData;
	}

	public void setErrorData(ErrorData errorData) {
		this.errorData = errorData;
	}
}