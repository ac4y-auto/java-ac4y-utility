package ac4y.utility;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import ac4y.base.ErrorHandler;

public class XSLHandler {

    public static String getResult(String aXml, String aXsl) throws TransformerException{
        
    	StringReader xmlReader = new StringReader(aXml);
    	StringReader xslReader = new StringReader(aXsl);
        StringWriter writer = new StringWriter();    	
    	
        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer;
			transformer = tFactory.newTransformer(
			        //new javax.xml.transform.stream.StreamSource(aXsl)
					new javax.xml.transform.stream.StreamSource(xslReader)
			);
			
	        transformer.transform(
	                new javax.xml.transform.stream.StreamSource(xmlReader), 
	                new javax.xml.transform.stream.StreamResult(writer));

			
        return writer.toString();        
    	
    }
    
} // XSLHandler
