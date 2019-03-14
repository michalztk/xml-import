package com.uploader.parser.sax;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Test;

import com.uploader.entity.EpaperRequest;
import com.uploader.exceptions.ParsingException;
import com.uploader.exceptions.SchemaException;

public class EpaperRequestXMLParserSAXTest {
	private EpaperRequestXMLParserSAX parser = new EpaperRequestXMLParserSAX();
	private String xmlData = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n" + "<epaperRequest>\r\n"
			+ " <deviceInfo name=\"Browser\" id=\"seba@seba-asus\">\r\n"
			+ "   <screenInfo width=\"1280\" height=\"752\" dpi=\"160\" />\r\n"
			+ "   <osInfo name=\"Browser\" version=\"1.0\" />\r\n" + "   <appInfo>\r\n"
			+ "            <newspaperName>abb</newspaperName>\r\n" + "             <version>1.0</version>\r\n"
			+ " </appInfo>\r\n" + "  </deviceInfo>\r\n"
			+ " <getPages editionDefId=\"11\" publicationDate=\"2017-06-06\"/>\r\n" + "</epaperRequest>";

	@Test
	public void validate() {
		InputStream is = new ByteArrayInputStream(xmlData.getBytes());
		try {
			parser.setSchemaLocation("C://iapps//schema//schema.xsd");
			assertEquals(true, parser.validate(is));
		} catch (SchemaException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void parse() {
		InputStream is = new ByteArrayInputStream(xmlData.getBytes());
		try {
			EpaperRequest request = parser.parse(is);
			assertEquals(160, request.getDpi());
		} catch (ParsingException e) {
			e.printStackTrace();
		}
	}

}
