package optimax.bidder.base.data;

import java.util.List;

public class DataProcessingModule {

	private DataProcessingModule() {
		//this class cannot be instanciated
	}
	
	
	/**
	 * Calculate the average value in a list if not empty
	 * @param bids
	 * @return
	 */
	public static Integer average(List<Integer> bids) {
		if (bids.size() < 1) {
			return 0;
		}

		int av = 0;
		for (Integer bid : bids) {
			av += bid;
		}
		return Math.round(av / bids.size());
	}
	
	/**
	 * Check if values from a start index -> are escalating
	 * 
	 * @param list
	 * @param indexStart
	 * @return
	 */
	public static boolean isListValueEscalating(List<Integer> list, Integer indexStart) {
		boolean result = false;
		if (list.size() - indexStart >= 5) {
			for (int i = indexStart; i < list.size() - 1; i++) {
				if (list.get(i) > list.get(i + 1)) {
					result = false;
				}
			}
			indexStart = list.size() - 1;
			result = true;
		}
		return result;
	}
	
}
