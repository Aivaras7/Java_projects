import java.util.HashMap;
import java.util.Map;

public class BuyLithium { // This class is responsible for providing user with the Lithium by price or
							// grade
	HashMap<Integer, Double> lithiumPrices = new HashMap<Integer, Double>();
	HashMap<Integer, Double> highQuality = new HashMap<Integer, Double>();
	LithiumPricing lithium = new LithiumPricing();

	public void findBestPrice(Double value) { // Stores prices hashmap from LithiumPricing class into local prices

		lithiumPrices = (HashMap<Integer, Double>) lithium.getPrice(); // Stores prices hashmap from LithiumPricing class into
																// local prices
		if (lithiumPrices.containsValue(value)) { // If statement to check if it contains the user wanted value for lithium
			for (Map.Entry<Integer, Double> entry : lithiumPrices.entrySet()) {
				if (entry.getValue() <= value) { // If value that is found is less or equal to user wanted value it is
													// then printed out
					System.out.println("Grade:" + entry.getKey() + " = £" + entry.getValue());
				}
			}
		}

	}

	public void findHighQuality(Integer grade) { // Same method as above but for grade instead of price
		lithiumPrices = (HashMap<Integer, Double>) lithium.getPrice();
		if (lithiumPrices.containsKey(grade)) {
			for (Map.Entry<Integer, Double> entry : lithiumPrices.entrySet()) {
				if (entry.getKey() >= grade) {
					System.out.println("Grade:" + entry.getKey() + " = £" + entry.getValue());
					highQuality.put(entry.getKey(), entry.getValue()); // Putting found grades into new hashmap
				}
			}
			System.out.println("Total amount Lithium located:" + highQuality.size()); // Displaying total amount of
																						// lithium stored in hashmap
			highQuality.clear();
		}

	}
}