/**
 * This class reads application specific data from appname.xml
 * 
 * To read a data from xml, use the below code
 * String article = data.getText("article")
 */
package au.wow.wlp.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.testng.Assert;

public class XMLDataReader {
	
	private Logger log;
	File xmlfile;
	SAXBuilder saxbuilder;
	Document document;
	Element rootelement, actualelement;
	List<Element> childelements;
	
	public XMLDataReader(Logger log){
		this.log=log;
	}
	
	/**
	 * Loads the test data xml specific to the application tests
	 * @param file
	 */
	public void loadTestDataXML(String file){
		xmlfile = new File(file);
		saxbuilder= new SAXBuilder();
		try {
			//Create a document object by building the file
			document = saxbuilder.build(xmlfile);
		} catch (JDOMException e) {
			Assert.fail("Error in working with JDOM library: " + e.getMessage());
		} catch (IOException e) {
			Assert.fail("Error in loading xml file: ", e.fillInStackTrace());
		}
		// Getting the root element from the xml document
		rootelement = document.getRootElement();
		childelements = rootelement.getChildren();
		log.info("Elements: " + childelements.toString());
	}
	

	public void getTCNameFromChildren(String TCName){
//		loadTestDataXML(file);
		String attrvalue = null;
		boolean isTCPresent=false;
		for(Element element : childelements){
			attrvalue = element.getAttribute("name").getValue().trim();
//			log.info("Attribute value: " + attrvalue);
			if(attrvalue.contains(TCName)){
				isTCPresent=true;
				actualelement=element;
				break;
			}
		}
//		if(!isTCPresent){
////			throw new SkipException("Testdata is not available in xml for the given test case name: " + TCName);
//			Assert.fail("Testdata is not available in xml for the given test case name: "+ TCName);
//		}
		
	}
	
	/**
	 * Retrieves the data as string
	 * @param node
	 * @return String data
	 */
	public String getText(String node){
		String nodevalue =  actualelement.getChild(node).getText();
		if(nodevalue==null){
			log.error("For Node: " + node + " value is null");
		}
		return nodevalue;
	}
	
	/**
	 * Retrieves the data as Int
	 * @param node
	 * @return integer data
	 */
	public int getInt(String node){	
		int nodevalue =  Integer.parseInt(actualelement.getChild(node).getText());
		if(nodevalue==0){
			log.error("For Node: " + node + " value is null");
		}
		return nodevalue;
	
	}
	
	/**
	 * Retrieves the data as double
	 * @param node
	 * @return double value
	 */
	public double getDouble(String node){
		double nodevalue =  Double.parseDouble(actualelement.getChild(node).getText());
		if(nodevalue==0){
			log.error("For Node: " + node + " value is null");
		}
		return nodevalue;
	}
	

}
