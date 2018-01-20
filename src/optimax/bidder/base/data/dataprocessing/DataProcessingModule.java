package optimax.bidder.base.data.dataprocessing;

import java.util.List;
import java.util.stream.Collectors;

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

	/**
	 * 
	 * @param allBidding
	 * @return true if every loss sequence has the same size.
	 */
	public static boolean isLossCycle(List<BiddingData> allBidding) {

		List<BiddingData> allMyLoss = allLostTurns(allBidding);

		int[] turnLossRep = getListOfSequenceOfStraightLosses(allMyLoss);

		for (int i = 0; i < turnLossRep.length - 1; i++) {
			if (turnLossRep[i] != turnLossRep[i + 1]) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Iterate though the list of losses and check the sequence of turns.
	 * @param allMyLoss
	 * @return list of ints that represent how many turns were lost in a row
	 */
	private static int[] getListOfSequenceOfStraightLosses(List<BiddingData> allMyLoss) {
		int[] turnLossRep = new int[allMyLoss.size()];
		int turnCounter = 0;
		int currentTurnLoss = 0;
		int index = 0;
		int maxTurnLoss = 0;

		for (BiddingData biddingData : allMyLoss) {
			if (turnCounter == 0) {
				turnCounter = biddingData.getTurn();
			} else {
				if (turnCounter == biddingData.getTurn() + 1) {
					currentTurnLoss++;
				} else {
					turnLossRep[index++] = currentTurnLoss;
					if (currentTurnLoss > maxTurnLoss) {
						maxTurnLoss = currentTurnLoss;
					}
					currentTurnLoss = 0;
				}
			}
		}
		return turnLossRep;
	}

	/**
	 * stream through the list of biddings and return a list of biddings that resulted in a loss
	 * @param allBidding
	 * @return all biddings that I lost
	 */
	private static List<BiddingData> allLostTurns(List<BiddingData> allBidding) {
		return allBidding.stream().filter(biddData -> biddData.getResult() < 0)
				.collect(Collectors.toList());
	}

}
