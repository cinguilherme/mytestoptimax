package optimax.bidder.behaviourcontrol.impl;

import optimax.bidder.BaseBidder;
import optimax.bidder.behaviourcontrol.BehaviorMultiplierEnum;
import optimax.bidder.behaviourcontrol.Behaviour;
import optimax.bidder.behaviourcontrol.BehaviourStrategyEnum;

public class Safe extends Behaviour {

	public Safe() {
		super(BehaviorMultiplierEnum.SHY);
		this.currentStrategy = BehaviourStrategyEnum.BAIT;
	}

	@Override
	public int getMyNextBidBasedOnBehaviorAndConditions(BaseBidder bidder) {
		
		return 1;
	}

	@Override
	public int opener() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int respond() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int bait() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int trade() {
		// TODO Auto-generated method stub
		return 0;
	}

}
