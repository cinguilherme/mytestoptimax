package optimax.bidder.base.data.intel;

import java.util.List;
import java.util.stream.Collectors;

import optimax.bidder.base.data.BiddingData;

class CyclicalIdentificationModule {

	private CyclicalIdentificationModule() {
		
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
