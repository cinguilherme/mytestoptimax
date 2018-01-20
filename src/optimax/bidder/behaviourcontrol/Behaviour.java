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

	public void reEvaluateStrategy(BaseBidder bidder) {
		//default does not reavaluate.
	}

	public int opener(BaseBidder bidder) {
		return 0;
	}

	public int respond(BaseBidder bidder) {
		return bidder.opponentData.lastBid + 1;
	}

	public int bait(BaseBidder bidder) {
		if (bidder.opponentData.lastBid > bidder.opponentData.averageWinningBid() * 1.2) {
			return 0;
		}

		return Math.round(bidder.opponentData.averageWinningBid() * 1.1F);
	}

	public int trade(BaseBidder bidder) {
		if (bidder.diferenceInQuantity() > 2) {
			return 0;
		} else {
			return respond(bidder);
		}
	}

}
