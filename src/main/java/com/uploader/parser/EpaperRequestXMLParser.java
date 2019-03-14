package com.uploader.parser;

import java.io.InputStream;

import com.uploader.entity.EpaperRequest;
import com.uploader.exceptions.ParsingException;
import com.uploader.exceptions.SchemaException;

public interface EpaperRequestXMLParser {
	public boolean validate(InputStream is) throws SchemaException;

	public EpaperRequest parse(InputStream is) throws ParsingException;
}
