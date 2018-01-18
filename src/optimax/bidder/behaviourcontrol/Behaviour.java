package optimax.bidder.behaviourcontrol;

import optimax.bidder.BaseBidder;

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
		case SHY:
			this.intensity = BehaviorMultiplierEnum.MODERATE;
			break;
		case MODERATE:
			this.intensity = BehaviorMultiplierEnum.AGRESSIVE;
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
		default:
			break;
		}
	}
	
	public int getMyNextBidBasedOnBehaviorAndConditions(BaseBidder bidder) {
		if (bidder.numberOfBids == 0) {
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
			case SCARE:
				nextBid = this.scare(bidder);
				break;
			case SEEK:
				nextBid = this.seek(bidder);
				break;
			default:
				break;
			}
			return nextBid;
		}		
	}
	
	public abstract void reEvaluateStrategy(BaseBidder bidder);
	
	public abstract int opener(BaseBidder bidder);
	
	public abstract int respond(BaseBidder bidder);
	
	public abstract int bait(BaseBidder bidder);
	
	public abstract int trade(BaseBidder bidder);
	
	public abstract int scare(BaseBidder bidder);
	
	public abstract int seek(BaseBidder bidder);
}
