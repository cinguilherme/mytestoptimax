package optimax.bidder.base.data.intel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import optimax.bidder.base.data.BiddingData;

public class CyclicalIdentificationModule {

	static int turnCounter = 0;
	static int currentTurnLoss = 1;

	static boolean lossInCycle = false;

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

		final int ruleCounter = turnLossRep.get(0);
		lossInCycle = true;

		turnLossRep.stream().forEach(i -> {
			if (i != ruleCounter) {
				lossInCycle = false;
			} else if (i == 0) { // this sounds wrong, here I should evaluate if all losses are 1 and
				lossInCycle = false;
			}
		});

		return lossInCycle;
	}

	/**
	 * Iterate though the list of losses and check the sequence of turns.
	 * 
	 * @param allMyLoss
	 * @return list of ints that represent how many turns were lost in a row
	 */
	private static List<Integer> getListOfSequenceOfStraightLosses(List<BiddingData> allMyLoss) {

		List<Integer> turnLossRep = new ArrayList<Integer>();
		turnCounter = 0;
		currentTurnLoss = 1;
		allMyLoss.stream().forEach(loss -> {
			if (turnCounter == 0) {
				turnCounter = loss.getTurn();
			} else {
				if (turnCounter + 1 == loss.getTurn()) {
					currentTurnLoss++;
				} else {
					turnLossRep.add(currentTurnLoss);
					currentTurnLoss = 1;
					turnCounter = loss.getTurn();
				}
			}
		});

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
		return allBidding.parallelStream().filter(biddData -> biddData.getResult() < 0).collect(Collectors.toList());
	}
}
