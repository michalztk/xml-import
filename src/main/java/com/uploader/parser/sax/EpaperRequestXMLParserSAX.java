package com.uploader.parser.sax;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.uploader.entity.EpaperRequest;
import com.uploader.exceptions.ParsingException;
import com.uploader.exceptions.SchemaException;
import com.uploader.parser.EpaperRequestXMLParser;

/**
 * SAX parser to parse XML with epaper requests
 * 
 * @author michal
 *
 */
@Component
@PropertySource("classpath:application.properties")
public class EpaperRequestXMLParserSAX implements EpaperRequestXMLParser {
	@Value("${schema.location}")
	private String schemaLocation; //read schema location from application.properties

	/**
	 * Validated given XML file against XSD schema
	 */
	@Override
	public boolean validate(InputStream is) throws SchemaException {
		File schemaFile = new File(schemaLocation);
		if (!schemaFile.exists())
			throw new SchemaException("Missing schema file.");

		try {
			String schemaLanguage = XMLConstants.W3C_XML_SCHEMA_NS_URI;
			SchemaFactory factory = SchemaFactory.newInstance(schemaLanguage);
			Schema schema = factory.newSchema(schemaFile);

			Validator validator = schema.newValidator();
			SAXSource source = new SAXSource(new InputSource(is));

			validator.validate(source);
			return true; // XML file is valid
		} catch (SAXException e1) {
			return false; // XML file is invalid
		} catch (IOException e2) {
			throw new SchemaException("Missing schema file.");
		}
	}

	/**
	 * Parses XML file
	 */
	@Override
	public EpaperRequest parse(InputStream is) throws ParsingException {
		EpaperRequest request = null;
		SAXParser parser = null;
		EpaperRequestHandler handler = new EpaperRequestHandler();

		SAXParserFactory factory = SAXParserFactory.newInstance();

		try {
			parser = factory.newSAXParser();
			parser.parse(is, handler);
			request = handler.getEpaperRequest();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			throw new ParsingException("File cannot be saved.");
		}
		return request;
	}

	public String getSchemaLocation() {
		return schemaLocation;
	}

	public void setSchemaLocation(String schemaLocation) {
		this.schemaLocation = schemaLocation;
	}

}
