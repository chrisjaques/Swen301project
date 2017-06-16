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

	/* • Amount of Mail: The amount of mail (by total volume and total weight and total
number of items) delivered to each destination from each origin. */


	//vol
//	weight
//	count
	
	//dest -> origin
	public static ArrayList<String> amountOfMail(){
		
		ArrayList<String> mailList = new ArrayList<String>();
		ArrayList<MailCount> mailCountList = new ArrayList<MailCount>();
		
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		Document xml;
		
		try {
			
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			// parse using the builder to get the DOM mapping of the XML file
			xml = docBuilder.parse("resources/logfile.xml");
			xml.getDocumentElement().normalize();
			
			Element root = xml.getDocumentElement(); //getAttributes()
			NodeList nl = root.getElementsByTagName("mail");
			
			for (int temp = 0; temp < nl.getLength(); temp++) {
				
				String destination = "";
		        String origin = "";
		        int volume = 0;
		        int weight = 0;
		        
				Node nNode = nl.item(temp);
				if(nNode.getNodeType()==Node.ELEMENT_NODE) {
			        NodeList nl2 = nNode.getChildNodes();
			        
			        for(int i2=0; i2<nl2.getLength(); i2++) {
			            Node n = nl2.item(i2);

			            if(n.getNodeName().equals("destination")){
			            	destination = n.getFirstChild().getTextContent();
			            } else if(n.getNodeName().equals("origin")){
			            	origin = n.getFirstChild().getTextContent();
			            } else if(n.getNodeName().equals("volume")){
			            	volume = Integer.parseInt(n.getFirstChild().getTextContent());
			            } else if(n.getNodeName().equals("weight")){
			            	weight = Integer.parseInt(n.getFirstChild().getTextContent());
			            }
			        }
			        System.out.println(destination + " " + origin);
		        }
				
				//if(mailCountList.contains())
				Boolean present = false;
				System.out.println(mailCountList.size());
				for(MailCount mail : mailCountList){
					System.out.println(mail.getDestination() + mail.getOrigin());
					if(mail.getDestination().equals(destination) && mail.getOrigin().equals(origin)){
						// add to this
						System.out.println("ssss");
						System.out.println(mail.volume);
						
						mail.addData(volume, weight);
						System.out.println(mail.volume);
						present = true;
						break;
					}
				}
				if(!present){
					
					MailCount mail = new MailCount(volume, weight, destination, origin);
					System.out.println("here");
					mailCountList.add(mail);
					System.out.println(mailCountList.contains(mail));
				}
				
				System.out.println(mailCountList.size());

			}
			
			for(MailCount mail : mailCountList){
				mailList.add("Amount of mail going from " + mail.getOrigin() + " to " + mail.getDestination() + ": Mail count - " + mail.getCount() + ", Total volume - " + mail.getVolume() + ", Total weight - " + mail.getWeight());
			}
			
			for(String mail : mailList){
				System.out.println(mail);
			}
			
		  } catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		
		  } catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return mailList;
	}
	
	private static class MailCount{
		
		private int volume;
		private int weight;
		private String destination;
		private String origin;
		private int count;
		
		private MailCount(int volume, int weight, String dest, String origin){
			this.volume = volume;
			this.weight = weight;
			this.destination = dest;
			this.origin = origin;
			this.count = 1;
		}
		
		public void addData(int volume2, int weight2) {
			this.volume += volume2;
			this.weight += weight2;
			this.count++;
		}

		private String getDestination(){
			return this.destination;
		}
		
		private String getOrigin(){
			return this.origin;
		}
		
		private int getCount(){
			return this.count;
		}
		
		private int getVolume(){
			return this.volume;
		}
		
		private int getWeight(){
			return this.weight;
		}
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
