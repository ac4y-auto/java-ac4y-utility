package ac4y.utility;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

public class JaxbCap {
	
	public Marshaller getMarshaller(String aPackageName) throws JAXBException{

		Marshaller vMarshaller = null;
		
		JAXBContext vJAXBContext = JAXBContext.newInstance(aPackageName);
		
		vMarshaller = vJAXBContext.createMarshaller();
        vMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
        return vMarshaller;
		
	} // getMarshaller

	public Unmarshaller getUnMarshaller(String aPackageName) throws JAXBException{

		Unmarshaller vUnmarshaller = null;
		
		JAXBContext  vJAXBContext = JAXBContext.newInstance(aPackageName);
		vUnmarshaller = vJAXBContext.createUnmarshaller();			
		
        return vUnmarshaller;
		
	} // getUnMarshaller

	public Marshaller getMarshaller(Class aClass) throws JAXBException{

		Marshaller vMarshaller = null;
		
		JAXBContext vJAXBContext = JAXBContext.newInstance(aClass);
		
		vMarshaller = vJAXBContext.createMarshaller();
	    vMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
        return vMarshaller;
		
	} // getMarshaller

	public Unmarshaller getUnMarshaller(Class aClass) throws JAXBException{

		Unmarshaller vUnmarshaller = null;
		
		JAXBContext  vJAXBContext = JAXBContext.newInstance(aClass);
		vUnmarshaller = vJAXBContext.createUnmarshaller();			
			
        return vUnmarshaller;
		
	} // getUnMarshaller

	public String getObjectAsXmlString(String aPackageName, Object aObject) throws JAXBException{

		String vXml = null;
		
		Marshaller vMarshaller;

	    StringWriter stringWriter = new StringWriter();
	    
	    getMarshaller(aPackageName).marshal(aObject,stringWriter);

	    vXml = stringWriter.toString();	
		
	    return vXml;
	    
	} // getObjectAsXmlString
	
	public Object getObjectFromXmlString(String aPackageName, String aXml) throws JAXBException{

		Object vObject = null;
		
		StringBuffer vStringBuffer = new StringBuffer(aXml);

		vObject = getUnMarshaller(aPackageName).unmarshal( new StreamSource( new StringReader( vStringBuffer.toString() ) ) );
		
	    return vObject;
	    
	} // getObjectFromXmlString

	public String getObjectAsXmlString(Class aClass, Object aObject) throws JAXBException{

		String vXml = null;
		
		Marshaller vMarshaller;

	    StringWriter stringWriter = new StringWriter();
	    
	    getMarshaller(aClass).marshal(aObject,stringWriter);

	    vXml = stringWriter.toString();	
		
	    return vXml;
	    
	} // getObjectAsXmlString
	
	public Object getObjectFromXmlString(Class aClass, String aXml) throws JAXBException{

		Object vObject = null;
		
		StringBuffer vStringBuffer = new StringBuffer(aXml);

		vObject = getUnMarshaller(aClass).unmarshal( new StreamSource( new StringReader( vStringBuffer.toString() ) ) );
		
	    return vObject;
	    
	} // getObjectFromXmlString
	
} // JaxbCap