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
	public int opener(BaseBidder bidder) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int respond(BaseBidder bidder) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int bait(BaseBidder bidder) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int trade(BaseBidder bidder) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int scare(BaseBidder bidder) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int seek(BaseBidder bidder) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void reEvaluateStrategy(BaseBidder bidder) {
		// TODO Auto-generated method stub
		
	}


}
