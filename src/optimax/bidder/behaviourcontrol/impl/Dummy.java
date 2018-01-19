package optimax.bidder.behaviourcontrol.impl;

import optimax.bidder.base.BaseBidder;
import optimax.bidder.behaviourcontrol.BehaviorMultiplierEnum;
import optimax.bidder.behaviourcontrol.Behaviour;
import optimax.bidder.behaviourcontrol.BehaviourStrategyEnum;

public class Dummy extends Behaviour {

	public Dummy() {
		super(BehaviorMultiplierEnum.SHY);
		this.currentStrategy = BehaviourStrategyEnum.TRADE;
	}

	@Override
	public void reEvaluateStrategy(BaseBidder bidder) {

	}

	@Override
	public int opener(BaseBidder bidder) {
		return 0;
	}

	@Override
	public int respond(BaseBidder bidder) {
		return 0;
	}

	@Override
	public int bait(BaseBidder bidder) {
		return 0;
	}

	@Override
	public int trade(BaseBidder bidder) {
		return bidder.opponentData.lastBid + 1;
	}

	@Override
	public int scare(BaseBidder bidder) {
		return 0;
	}

	@Override
	public int seek(BaseBidder bidder) {
		return 0;
	}

}
