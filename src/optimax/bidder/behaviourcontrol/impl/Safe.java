package optimax.bidder.behaviourcontrol.impl;

import optimax.bidder.BaseBidder;
import optimax.bidder.behaviourcontrol.BehaviorMultiplierEnum;
import optimax.bidder.behaviourcontrol.Behaviour;
import optimax.bidder.behaviourcontrol.BehaviourStrategyEnum;

public class Safe extends Behaviour {

	public Safe() {
		super(BehaviorMultiplierEnum.SHY);
		this.currentStrategy = BehaviourStrategyEnum.SEEK;
	}

	/**
	 * This behavior class starts with zero because it focus on 
	 * trying to outbid de opponent after the first bid.
	 */
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
		return 0;
	}

	@Override
	public int scare(BaseBidder bidder) {
		return 0;
	}

	/**
	 * This behavior tries to bid 20% over the last bid of the opponent if winning, if lossing try 35% more.
	 */
	@Override
	public int seek(BaseBidder bidder) {
		float multiplier = 1.2F;
		if(bidder.opponnentQuantity > bidder.quantity) {
			multiplier = 1.35F;
		}
		return (int) Math.round(bidder.opponentLastBid * multiplier);
	}

	@Override
	public void reEvaluateStrategy(BaseBidder bidder) {
		
		
		
	}


}
