package optimax.bidder.behaviourcontrol.impl;

import optimax.bidder.base.BaseBidder;
import optimax.bidder.behaviourcontrol.BehaviorMultiplierEnum;
import optimax.bidder.behaviourcontrol.Behaviour;
import optimax.bidder.behaviourcontrol.BehaviourStrategyEnum;
import optimax.bidder.behaviourcontrol.DiferenceRelativeToAmount;

public class Safe extends Behaviour {

	public Safe() {
		super(BehaviorMultiplierEnum.SHY);
		this.currentStrategy = BehaviourStrategyEnum.SEEK;
	}
	
	public Safe(BehaviorMultiplierEnum mult, BehaviourStrategyEnum strategy) {
		super(mult);
		this.currentStrategy = strategy;
	}

	/**
	 * This behavior class starts with zero because it focus on trying to outbid de
	 * opponent after the first bid.
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
	 * This behavior tries to bid 20% over the last bid of the opponent if winning,
	 * if lossing try 35% more.
	 */
	@Override
	public int seek(BaseBidder bidder) {
		return (int) Math.round(bidder.opponentData.lastBid * this.intensity.getCodeMultiplier());
	}

	@Override
	public void reEvaluateStrategy(BaseBidder bidder) {

		int winning = bidder.diferenceInQuantity();
		int difSpen = bidder.diferenceInSpense();
		DiferenceRelativeToAmount diferenceQuantity = bidder.difInQuantity();
		DiferenceRelativeToAmount diferenceCash = bidder.difInCash();

		if (winning <= 0) {
			evaluateWinning(difSpen, diferenceQuantity, diferenceCash);
		} else {
			evaluateLosing(difSpen, diferenceQuantity);
		}
	}

	/**
	 * how good is it? or is it good really? keep the same winning and spending
	 * less. make no change please. winning but spending more? reevaluate
	 * 
	 * @param difSpen
	 * @param diferenceQuantity
	 * @param diferenceCash
	 */
	private void evaluateWinning(int difSpen, DiferenceRelativeToAmount diferenceQuantity,
			DiferenceRelativeToAmount diferenceCash) {

		if (difSpen < 0) {
			changeIntensityWinningAndSpendingMore(diferenceQuantity, diferenceCash);
		}
	}

	/**
	 * i'm loosing and spending more? This is bad if i'm loosing and spending the
	 * same amount? raize intensity.
	 * 
	 * @param difSpen
	 * @param diferenceQuantity
	 */
	private void evaluateLosing(int difSpen, DiferenceRelativeToAmount diferenceQuantity) {
		if (difSpen > 0) {
			changeIntensityLosingAndSpendingLess(diferenceQuantity);
		} else if (difSpen < 0) {
			// dont know what to do here.. maybe go for zeros until the diference balances out?
			changeIntensity(BehaviorMultiplierEnum.HOLD);
		} else {
			// if i'm loosing and spending the same amount, raize intensity.
			raizeIntensity();
		}
	}

	/**
	 * In case winning but spending much more?
	 * @param diferenceQuantity
	 * @param diferenceCash
	 */
	private void changeIntensityWinningAndSpendingMore(DiferenceRelativeToAmount diferenceQuantity,
			DiferenceRelativeToAmount diferenceCash) {

		if (diferenceQuantity.equals(DiferenceRelativeToAmount.MODERATE)
				&& diferenceCash.equals(DiferenceRelativeToAmount.LARGE)) {
			lowerIntensity();
		}

	}

	private void changeIntensityLosingAndSpendingLess(DiferenceRelativeToAmount diferenceQuantity) {
		switch (diferenceQuantity) {
		case SMALL:
			raizeIntensity();
			break;
		case MODERATE:
			raizeIntensity();
			break;
		case LARGE:
			raizeIntensity();
			raizeIntensity();
		default:
			break;
		}
	}

}
