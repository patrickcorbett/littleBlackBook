package com.pcorbett.littleBlackBook.exceptions;

public abstract class AbstractApplicationException extends RuntimeException {

	private static final long serialVersionUID = -5916930674226506926L;

	public AbstractApplicationException(String pMessage) {
		super(pMessage);
	}

	public AbstractApplicationException(String pMessage, Throwable pCause) {
		super(pMessage, pCause);
	}
}
