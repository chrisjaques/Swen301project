package event_logging;
import java.io.IOException;
import java.util.ArrayList;

import javax.management.modelmbean.XMLParseException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadXMLData {

	
public static ArrayList<String> readXML(){
		
		ArrayList<String> elements = new ArrayList<String>();
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		Document xml;
		
		try {
			
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			// parse using the builder to get the DOM mapping of the XML file
			xml = docBuilder.parse("resources/logfile.xml");
			xml.getDocumentElement().normalize();
			
			Element root = xml.getDocumentElement(); //getAttributes()
			NodeList nl = root.getChildNodes();
			
			for (int temp = 0; temp < nl.getLength(); temp++) {

				Node nNode = nl.item(temp);

				switch (nNode.getNodeName()) {
				case "mail":
					elements.add(ProcessData.processMail(nNode));
					break;
				case "cost":
					elements.add(ProcessData.processCost(nNode));
					break;
				case "route":
					elements.add(ProcessData.processRoute(nNode));
					break;
				case "user":
					elements.add(ProcessData.processUser(nNode));
					break;
				case "#text":
					
					break;
				default:
					throw new XMLParseException("Invalid XML parsed");
				}

			}
			
		  } catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		
		  } catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XMLParseException e) {
			e.printStackTrace();
		}

		for (int temp = 0; temp < elements.size(); temp++) {
			System.out.println(elements.get(temp));
		}
		return elements;
	}

	public static ArrayList<String> readXMLFromTimePeriod(){
		
		ArrayList<String> elements = new ArrayList<String>();
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		Document xml;
		
		try {
			
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			// parse using the builder to get the DOM mapping of the XML file
			xml = docBuilder.parse("resources/logfile.xml");
			xml.getDocumentElement().normalize();
			
			Element root = xml.getDocumentElement(); //getAttributes()
			NodeList nl = root.getChildNodes();
			
			for (int temp = 0; temp < nl.getLength(); temp++) {

				Node nNode = nl.item(temp);

				switch (nNode.getNodeName()) {
				case "mail":
					elements.add(ProcessData.processMail(nNode));
					break;
				case "cost":
					elements.add(ProcessData.processCost(nNode));
					break;
				case "route":
					elements.add(ProcessData.processRoute(nNode));
					break;
				case "user":
					elements.add(ProcessData.processUser(nNode));
					break;
				case "#text":
					
					break;
				default:
					throw new XMLParseException("Invalid XML parsed");
				}

			}
			
		  } catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		
		  } catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XMLParseException e) {
			e.printStackTrace();
		}

		for (int temp = 0; temp < elements.size(); temp++) {
			System.out.println(elements.get(temp));
		}
		return elements;
	}
	
}
