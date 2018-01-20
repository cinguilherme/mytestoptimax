package optimax.bidder.behaviourcontrol;

import optimax.bidder.base.BaseBidder;
import optimax.bidder.behaviourcontrol.enums.BehaviorMultiplierEnum;
import optimax.bidder.behaviourcontrol.enums.BehaviourStrategyEnum;

public abstract class Behaviour {

	protected BehaviorMultiplierEnum intensity;
	protected BehaviourStrategyEnum currentStrategy;

	public Behaviour(BehaviorMultiplierEnum type) {
		this.intensity = type;
	}

	protected void changeIntensity(BehaviorMultiplierEnum intesity) {
		this.intensity = intesity;
	}

	/**
	 * raize the intensity
	 */
	protected void raizeIntensity() {
		switch (this.intensity) {
		case HOLD:
			this.intensity = BehaviorMultiplierEnum.SHY;
			break;
		case SHY:
			this.intensity = BehaviorMultiplierEnum.MODERATE;
			break;
		case MODERATE:
			this.intensity = BehaviorMultiplierEnum.AGRESSIVE;
			break;
		default:
			break;
		}
	}

	/**
	 * lower the intensity
	 */
	protected void lowerIntensity() {
		switch (this.intensity) {
		case AGRESSIVE:
			this.intensity = BehaviorMultiplierEnum.MODERATE;
			break;
		case MODERATE:
			this.intensity = BehaviorMultiplierEnum.SHY;
			break;
		case SHY:
			this.intensity = BehaviorMultiplierEnum.HOLD;
			break;
		default:
			break;
		}
	}

	/**
	 * Get the next bid value depending on the current strategy.
	 * 
	 * @param bidder
	 * @return
	 */
	public int getMyNextBidBasedOnBehaviorAndConditions(BaseBidder bidder) {
		if (bidder.data.numberOfBids == 0) {
			return opener(bidder);
		} else {
			int nextBid = 0;
			switch (this.currentStrategy) {
			case TRADE:
				nextBid = this.trade(bidder);
				break;
			case BAIT:
				nextBid = this.bait(bidder);
				break;
			case RESPOND:
				nextBid = respond(bidder);
				break;
			default:
				break;
			}
			return nextBid;
		}
	}

	protected void reEvaluateStrategy(BaseBidder bidder) {
		// default does not reavaluate.
	}

	/**
	 * First play, basic opener is Zero.
	 * 
	 * @param bidder
	 * @return
	 */
	public int opener(BaseBidder bidder) {
		return 0;
	}

	/**
	 * Basic response is always the last bid of the opponent + 1, times the
	 * multiplier of the intensity.
	 * 
	 * @param bidder
	 * @return
	 */
	public int respond(BaseBidder bidder) {
		return (int) Math.round((bidder.opponentData.lastBid + 1) * intensity.getCodeMultiplier());
	}

	/**
	 * Basic bait is play zero if the opponent is playing over 20% the average
	 * winning bids he did, if not play the average winning bid of the oponnet +
	 * 10%, times the multiplier of the intensity.
	 * 
	 * @param bidder
	 * @return
	 */
	public int bait(BaseBidder bidder) {
		if (bidder.opponentData.lastBid > bidder.opponentData.averageWinningBid() * 1.2) {
			return 0;
		}

		return (int) Math.round((bidder.opponentData.averageWinningBid() * 1.1F) * intensity.getCodeMultiplier());
	}

	/**
	 * Basic trade is to not bid if the diference in quantity is above 10 units, if
	 * not.. just respond.
	 * 
	 * @param bidder
	 * @return
	 */
	public int trade(BaseBidder bidder) {
		if (bidder.diferenceInQuantity() > 10) {
			return 0;
		} else {
			return respond(bidder);
		}
	}

}
