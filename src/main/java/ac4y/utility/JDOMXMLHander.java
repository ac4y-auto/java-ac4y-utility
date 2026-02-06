package ac4y.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.filter.Filters;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;

public class JDOMXMLHander {

	public String getXMLString(Element aElement, String aEncoding) {

		Format vFormat = Format.getPrettyFormat();
		vFormat.setEncoding(aEncoding);
		XMLOutputter vXMLOutputter = new XMLOutputter(vFormat);

		return vXMLOutputter.outputString(aElement);
		
	} // getXMLString

	public Document documentFromXMLFile(String aFileName) throws JDOMException, IOException {
		
			return new SAXBuilder().build(new File(aFileName));
			
	} // documentFromXMLFile

	public Document documentFromXMLString(String aXmlString) throws JDOMException, IOException {

	    	return new SAXBuilder().build(new StringReader(aXmlString));
		
	} // documentFromXMLFile
	
	public void writeXMLDocumentToFile(Document aDocument, String aEncoding, String aFileName) throws FileNotFoundException, IOException {

		Format vFormat = Format.getPrettyFormat(); 
		vFormat.setEncoding(aEncoding);
		XMLOutputter vXMLOutputter = new XMLOutputter(vFormat);

		vXMLOutputter.output(aDocument, new FileOutputStream(aFileName));
		
	} // writeXMLDocumentToFile

	public String getChildTextValueByXPath(Element aBaseElement, String aXPath) {

		XPathExpression<Element> vXPath =
			    XPathFactory.instance().compile(
		    		aXPath
		    		,Filters.element()
			    );
		Element vElement = null;
		try  { vElement = vXPath.evaluateFirst(aBaseElement); } catch (Exception vException){}
		
		if (vElement != null) {
		    return vElement.getText();
		} else return "";
		
	} // getChildTextValueByXPath
	
	public XPathExpression<Element> xPathExpressionElement(String aExpression){

		return	XPathFactory.instance().compile(
					aExpression
					,Filters.element()
				);
		
	} // xPathExpressionElement
	
	public Element getObject(Element aContainer, String aName){

		return new JDOMXMLHander().xPathExpressionElement(aName).evaluate(aContainer).get(0);
		
	} // getObject

	public List<Element> getObjectCollection(Element aContainer, String aName){

		return new JDOMXMLHander().xPathExpressionElement(aName).evaluate(aContainer);
		
	} // getObjectCollection

	public Element getProperty(Element aObject, String aName){
		
		return getObject(aObject, aName);
		
	} // getProperty
	
	public String getPropertyValue(Element aObject, String aName){
		
		return aObject.getChildText(aName);
		
	} // getPropertyValue
	
	public String getPropertyValue(Element aContainer, String aObjectName, String aName){
		
		return getObject(aContainer, aObjectName).getChildText(aName);
		
	} // getPropertyValue
	
} // JDOMXMLHander