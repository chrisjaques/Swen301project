package logic;

import java.util.Comparator;

/**
 * Comparator for prioritizing DeliveryRoutes, land and sea routes automatically go ahead of air routes because standard delivery only uses air routes when land or sea arn't possible
 * the same Comparitor is used for air priority because it wont include any land or sea routes to compare
 * @author whitewill1
 *
 */
public class RouteComparitor implements Comparator<DeliveryRoute>{
	@Override
	public int compare(DeliveryRoute r1, DeliveryRoute r2) {
		if(r1.getPriority()){

			if(r2.getPriority()){

				return (r1.getTotalPrice()<r2.getTotalPrice()? -1:1); //if they are both air routes just compare by cost
			}
			return 1; //if r1 is an air route and r2 isn't, put r2 ahead of r1
		}
		if(r2.getPriority()){
			return -1; //if r1 is a land or sea route and r2 isn't then r1 gets put ahead of r2
		}

		return (r1.getTotalPrice()<r2.getTotalPrice()? -1:1); //if neither are air routes order them by cost
	}
}