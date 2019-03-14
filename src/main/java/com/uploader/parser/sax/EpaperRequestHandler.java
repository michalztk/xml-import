package com.uploader.parser.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.uploader.entity.EpaperRequest;

/**
 * Handler to create EpaperRequest object
 * 
 * @author michal
 *
 */
public class EpaperRequestHandler extends DefaultHandler {
	private EpaperRequest request;
	private boolean isNewspaperName = false;

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		switch (qName) {
		case "epaperRequest":
			request = new EpaperRequest();
			break;
		case "newspaperName":
			isNewspaperName = true;
			break;
		case "screenInfo":
			addScreenInfo(attributes);
			break;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (isNewspaperName) { // read newspaper name if it is newspaperName tag
			request.setNewspaperName(new String(ch, start, length));
			isNewspaperName = false;
		}
	}

	/**
	 * Reads screen info attributes
	 * 
	 * @param attributes
	 */
	private void addScreenInfo(Attributes attributes) {
		int length = attributes.getLength();
		for (int i = 0; i < length; i++) {
			String attrName = attributes.getQName(i);

			switch (attrName) {
			case "width":
				request.setWidth(Short.parseShort(attributes.getValue(i)));
				break;
			case "height":
				request.setHeight(Short.parseShort(attributes.getValue(i)));
				break;
			case "dpi":
				request.setDpi(Short.parseShort(attributes.getValue(i)));
				break;
			}
		}
	}

	/**
	 * Returns request object
	 * 
	 * @return
	 */
	public EpaperRequest getEpaperRequest() {
		return request;
	}
}