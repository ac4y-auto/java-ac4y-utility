package ac4y.utility;

import org.w3c.dom.Document;
import	org.w3c.dom.Node;
import	org.w3c.dom.NamedNodeMap;
import org.xml.sax.InputSource;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URLEncoder;
import java.net.URLDecoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class XMLHandler
{

	public String getCoded ( String aText ) {
		
		try {
		
			return URLEncoder.encode ( aText, "UTF-8" );
		}
		catch
		(
			Exception exception
		)
		{
			return "!! kodolasi hiba !!";
		}
		
	} // getCoded

	public String getDecoded ( String aText ) {
		
		try
		{
		
			return URLDecoder.decode ( aText, "UTF-8");
		}
		catch
		(
			Exception exception
		)
		{
			return "!! dekodolasi hiba !!";
		}
		
	} // getDecoded

	public String getAttributeValue ( Node aNode, String aName ) {
		
		Node			vNode;
		NamedNodeMap	vMap;
		
		if	(
				( aNode != null )
				&&
				aNode.hasAttributes()
			)
		{
			
			vMap 	= aNode.getAttributes();
			vNode	= vMap.getNamedItem ( aName );

			if	( vNode != null )
				return	vNode.getNodeValue();
			else
				return	"";

		} 
		else 
			return "";
		
	} // getAttributeValue
	
	public String getDecodedAttributeValue ( Node aNode, String aName ) {
		
		return	getDecoded ( getAttributeValue ( aNode, aName ) );
		
	} // getDecodedAttributeValue
	
	public Document documentFromXMLString(String aXMLString) {

		Document vDocument = null; 					
		
	    try {
		
	    	DocumentBuilderFactory 	vDocumentBuilderFactory 	= DocumentBuilderFactory.newInstance();
	    	DocumentBuilder 		vDocumentBuilder 			= vDocumentBuilderFactory.newDocumentBuilder();
	    	InputSource 			vInputSource				= new InputSource(new StringReader(aXMLString));
	    							vDocument 					= vDocumentBuilder.parse(vInputSource);
 
	    	vDocument.getDocumentElement().normalize();

	    } catch (Exception e) {
    		e.printStackTrace();
	    } // catch
	    	
    	return vDocument;
		
	} // documentFromXMLString
	
	public Document documentFromXMLFile(String aFileName) {

		Document vDocument = null; 					
		
	    try {
		
	    	File 					vFile 						= new File(aFileName);
		
	    	DocumentBuilderFactory 	vDocumentBuilderFactory 	= DocumentBuilderFactory.newInstance();
	    	DocumentBuilder 		vDocumentBuilder 			= vDocumentBuilderFactory.newDocumentBuilder();
	    							vDocument 					= vDocumentBuilder.parse(vFile);
 
	    	vDocument.getDocumentElement().normalize();

	    } catch (Exception e) {
    		e.printStackTrace();
	    } // catch
	    	
    	return vDocument;
		
	} // documentFromXMLFile
	
	public String getXMLStringFromDocument(Document aDocument)
	{
	    try
	    {
	       DOMSource vDOMSource = new DOMSource(aDocument);
	       StringWriter vWriter = new StringWriter();
	       StreamResult vResult = new StreamResult(vWriter);
	       TransformerFactory vTransformerFactory = TransformerFactory.newInstance();
	       Transformer vTransformer = vTransformerFactory.newTransformer();
//	       vTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
	       vTransformer.transform(vDOMSource, vResult);
	       
	       return vWriter.toString();
	       
	       
	       
	    }
	    catch(TransformerException ex)
	    {
	       ex.printStackTrace();
	       return null;
	    }
	    
	} // getXMLStringFromDocument		

} // XMLHandler