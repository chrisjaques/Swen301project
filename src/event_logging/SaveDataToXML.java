package event_logging;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import logic.Mail;
import logic.Route;
import logic.User;
import logic.Route.TransportType;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class SaveDataToXML {
	
	public static <T> void saveToXML(T eventObject){
				
		if (eventObject instanceof Route) {
	        parseRoute((Route) eventObject);
	    } else if (eventObject instanceof User) {
	        parseUser((User) eventObject);
	    } else if (eventObject instanceof Mail) {
	        parseMail((Mail) eventObject);
	    } else if (eventObject instanceof User) { //cost
	        parseUser((User) eventObject);
	    } else if (eventObject instanceof User) { //price
	        parseUser((User) eventObject);
	    } else if (eventObject instanceof User) { //discontinue
	        parseUser((User) eventObject);
	    } else {
	    	System.out.println("WOT IS GOING ON.");
	    }
	}
		
	private static void parseRoute(Route route) {
		
		HashMap<String, String> routeMap = new HashMap<String, String>();
		routeMap.put("Type", "route");
		routeMap.put("destination", route.getDestination());
		routeMap.put("origin", route.getOrigin());
		routeMap.put("price", Double.toString(route.getPrice()));
		routeMap.put("transport_type", route.getTransportType().toString());

		convertDataToXML(routeMap);
	}
	
	private static void parseUser(User user) {
		
		HashMap<String, String> userMap = new HashMap<String, String>();
		userMap.put("Type", "user");
		userMap.put("username", user.getUsername());
		userMap.put("password", user.getPassword());
		userMap.put("role", user.getRole().toString());
		
		convertDataToXML(userMap);	
	}
	
	private static void parseMail(Mail mail) {
		
		HashMap<String, String> mailMap = new HashMap<String, String>();
		mailMap.put("Type", "mail");
		mailMap.put("origin", mail.getOrigin());
		mailMap.put("destination", mail.getDestination());
		mailMap.put("volume", String.valueOf(mail.getVolume()));
		mailMap.put("weight", String.valueOf(mail.getWeight()));
		mailMap.put("priority", mail.getPriority().toString());
		mailMap.put("price", String.valueOf(mail.getPrice()));
		
		convertDataToXML(mailMap);	
	}

	private static void convertDataToXML(HashMap<String, String> eventMap) {
		
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		try {

			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	
			// root elements
			Document doc = docBuilder.parse("resources/logfile.xml");
			Element rootElement = doc.getDocumentElement(); // (events)
			
			Element eventType = doc.createElement(eventMap.get("Type"));
			eventMap.remove("Type");
			
			for (HashMap.Entry<String, String> entry : eventMap.entrySet()){
				Element ekey = doc.createElement(entry.getKey());
	            ekey.appendChild(doc.createTextNode(entry.getValue()));
	            eventType.appendChild(ekey);
			}
			
			rootElement.appendChild(eventType);

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult("resources/logfile.xml");
	
			// Output to console for testing
			transformer.transform(source, result);

		  } catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		  } catch (TransformerException tfe) {
			tfe.printStackTrace();
		  } catch (SAXException e) {
			//no file found 
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String argv[]) {
		
		//ArrayList<String> hello = ReadXMLData.amountOfMail();
		System.out.println(ReadXMLData.totalRevenue());
		//System.out
		//Route r = new Route("well", "auck", Route.TransportType.AIR, 1.00);
		//User u = new User("chloe", "graham", User.UserType.CLERK);
		//Mail m = new Mail(TransportType.AIR, 1.00,"test1", "test2", 20.00);
		//m.setPrice(18.50);
		//System.out.println(m.getPrice());
		//saveToXML(m);
		//saveToXML(u);
		  
	}
}
