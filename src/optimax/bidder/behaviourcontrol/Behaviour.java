package optimax.bidder.behaviourcontrol;

import optimax.bidder.BaseBidder;

public abstract class Behaviour {

	protected BehaviorMultiplierEnum style;
	protected BehaviourStrategyEnum currentStrategy;
	
	public Behaviour(BehaviorMultiplierEnum type) {
		this.style = type;
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
