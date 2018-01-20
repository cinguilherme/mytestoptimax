package optimax.bidder.base.data.intel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import optimax.bidder.base.data.BiddingData;

public class CyclicalIdentificationModule {

	private CyclicalIdentificationModule() {

	}

	/**
	 * 
	 * @param allBidding
	 * @return true if every loss sequence has the same size.
	 */
	public static boolean isLossCycle(List<BiddingData> allBidding) {

		List<BiddingData> allMyLoss = allLostTurns(allBidding);

		List<Integer> turnLossRep = getListOfSequenceOfStraightLosses(allMyLoss);

		for (int i = 0; i < turnLossRep.size() - 1; i++) {
			if (turnLossRep.get(i) != turnLossRep.get(i + 1)) {
				return false;
			} else if (turnLossRep.get(i) == 0) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Iterate though the list of losses and check the sequence of turns.
	 * 
	 * @param allMyLoss
	 * @return list of ints that represent how many turns were lost in a row
	 */
	private static List<Integer> getListOfSequenceOfStraightLosses(List<BiddingData> allMyLoss) {

		List<Integer> turnLossRep = new ArrayList<Integer>();
		int turnCounter = 0;
		int currentTurnLoss = 1;

		for (BiddingData biddingData : allMyLoss) {
			if (turnCounter == 0) {
				turnCounter = biddingData.getTurn();
			} else {
				if (turnCounter + 1 == biddingData.getTurn()) { // bug
					currentTurnLoss++;
				} else {
					turnLossRep.add(currentTurnLoss);
					currentTurnLoss = 1;
					turnCounter = biddingData.getTurn();
				}
			}
		}
		return turnLossRep;
	}

	/**
	 * stream through the list of biddings and return a list of biddings that
	 * resulted in a loss
	 * 
	 * @param allBidding
	 * @return all biddings that I lost
	 */
	private static List<BiddingData> allLostTurns(List<BiddingData> allBidding) {
		return allBidding.stream().filter(biddData -> biddData.getResult() < 0).collect(Collectors.toList());
	}
}
