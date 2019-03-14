package com.uploader.exceptions;

/**
 * Exception to handle errors during XML validation
 * @author michal
 *
 */
public class SchemaException extends Exception {
	private static final long serialVersionUID = 201401634018021629L;

	public SchemaException(String message) {
		super(message);
	}
}
