package optimax.bidder.base.data;

import java.util.List;
import java.util.stream.Collectors;

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

	public static boolean isThereALossCycle(List<BiddingData> allBidding) {

		List<BiddingData> allMyLoss = allBidding.stream().filter(biddData -> biddData.getResult() < 0)
				.collect(Collectors.toList());

		int turnCounter = 0;
		
		int currentTurnLoss = 0;
		
		int maxTurnLoss = 0;
		int[] maxTurnLossRep = new int[allMyLoss.size()];
		int index = 0;
		
		for (BiddingData biddingData : allMyLoss) {
			if (turnCounter == 0) {
				turnCounter = biddingData.getTurn();
			} else {
				if (turnCounter == biddingData.getTurn() + 1) {
					currentTurnLoss++;
				} else {
					maxTurnLossRep[index++] = currentTurnLoss;
					if (currentTurnLoss > maxTurnLoss) {
						maxTurnLoss = currentTurnLoss;
					}
					currentTurnLoss = 0;
				}
			}
		}

		return false;
	}

}
