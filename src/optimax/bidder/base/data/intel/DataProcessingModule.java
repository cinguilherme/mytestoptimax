package optimax.bidder.base.data.intel;

import java.util.List;

import optimax.bidder.base.data.BiddingData;

public class DataProcessingModule {

	private DataProcessingModule() {
		// this class cannot be instanciated
	}

	/**
	 * Calculate the average value in a list if not empty
	 * 
	 * @param bids
	 * @return
	 */
	public static Integer average(List<Integer> bids) {
		if (bids.size() < 1) {
			return 0;
		}
		return (int) bids.stream().mapToInt(i -> i).average().getAsDouble();
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

	/**
	 * 
	 * @param allBidding
	 * @return true if every loss sequence has the same size.
	 */
	public static boolean isLossCycle(List<BiddingData> allBidding) {
		return CyclicalIdentificationModule.isLossCycle(allBidding);
	}

}
