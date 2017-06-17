package event_logging;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


public class ProcessData {

	protected static String processMail(Node mail) {
		
		if (mail.getNodeType() == Node.ELEMENT_NODE) {

			Element eElement = (Element) mail;

			String mailData = "Mail: ";
			
			mailData += ("Destination - " + eElement.getElementsByTagName("destination").item(0).getTextContent().trim() + ", ");
			mailData += ("Origin - " + eElement.getElementsByTagName("origin").item(0).getTextContent().trim() + ", ");
			mailData += ("Volume - " + eElement.getElementsByTagName("volume").item(0).getTextContent() + ", ");
			mailData += ("Weight - " + eElement.getElementsByTagName("weight").item(0).getTextContent() + ", ");
			mailData += ("Price - " + eElement.getElementsByTagName("price").item(0).getTextContent() + ", ");
			mailData += ("Priority - " + eElement.getElementsByTagName("priority").item(0).getTextContent() + "\n");
			
			return mailData;
		}
		return null;
	}
	
	protected static String processRoute(Node route) {
		
		if (route.getNodeType() == Node.ELEMENT_NODE) {

			Element eElement = (Element) route;
			String routeData = "Route: ";
			
			routeData += ("Destination - " + eElement.getElementsByTagName("destination").item(0).getTextContent().trim()+ ", ");
			routeData += ("Origin - " + eElement.getElementsByTagName("origin").item(0).getTextContent().trim()+ ", ");
			routeData += ("Price - " + eElement.getElementsByTagName("price").item(0).getTextContent()+ ", ");
			routeData += ("Transport type - " + eElement.getElementsByTagName("transport_type").item(0).getTextContent()+ "\n ");

			return routeData;
		}
		return null;
	}

	protected static String processUser(Node user) {
		
		if (user.getNodeType() == Node.ELEMENT_NODE) {
	
			Element eElement = (Element) user;
			String userData = "User: ";
			
			userData += ("Username - " + eElement.getElementsByTagName("username").item(0).getTextContent()+ ", ");
			userData += ("Password - ******, ");
			userData += ("Role - " + eElement.getElementsByTagName("role").item(0).getTextContent()+ "\n ");
			return userData;
		}
		return null;
	}
	
	protected static String processCost(Node cost) {
		
		if (cost.getNodeType() == Node.ELEMENT_NODE) {

			Element eElement = (Element) cost;
			String costData = "Cost: ";
			
			costData += ("Company - " + eElement.getElementsByTagName("company").item(0).getTextContent()+ ", ");
			costData += ("Destination - " + eElement.getElementsByTagName("to").item(0).getTextContent()+ ", ");
			costData += ("Origin - " + eElement.getElementsByTagName("from").item(0).getTextContent()+ ", ");
			costData += ("Priority - " + eElement.getElementsByTagName("priority").item(0).getTextContent()+ ", ");
			costData += ("Volume Cost - " + eElement.getElementsByTagName("volumecost").item(0).getTextContent()+ ", ");
			costData += ("Weight Cost - " + eElement.getElementsByTagName("weightcost").item(0).getTextContent()+ ", ");
			costData += ("Maximum Weight - " + eElement.getElementsByTagName("maxweight").item(0).getTextContent()+ ", ");
			costData += ("Maximum Volume - " + eElement.getElementsByTagName("maxvolume").item(0).getTextContent()+ ", ");
			costData += ("Duration - " + eElement.getElementsByTagName("duration").item(0).getTextContent()+ ", ");
			costData += ("Frequency - " + eElement.getElementsByTagName("frequency").item(0).getTextContent()+ ", ");
			costData += ("Day - " + eElement.getElementsByTagName("day").item(0).getTextContent()+ "/n");
			
			return costData;
		}
		return null;
	}
}
