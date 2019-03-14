package com.uploader.exceptions;

/**
 * Exception to handle errors during XML parsing
 * @author michal
 *
 */
public class ParsingException extends Exception {
	private static final long serialVersionUID = 1367942218226552667L;

	public ParsingException(String message) {
		super(message);
	}
}
