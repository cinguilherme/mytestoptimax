package optimax.bidder.behaviourcontrol;

import optimax.bidder.BaseBidder;

public abstract class Behaviour {

	protected BehaviorMultiplierEnum style;
	protected BehaviourStrategyEnum currentStrategy;
	
	public Behaviour(BehaviorMultiplierEnum type) {
		this.style = type;
	}
	
	public abstract int getMyNextBidBasedOnBehaviorAndConditions(BaseBidder bidder);
	
	public abstract int opener();
	
	public abstract int respond();
	
	public abstract int bait();
	
	public abstract int trade();
}
