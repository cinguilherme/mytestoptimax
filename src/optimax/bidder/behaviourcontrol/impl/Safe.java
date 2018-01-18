package optimax.bidder.behaviourcontrol.impl;

import optimax.bidder.BaseBidder;
import optimax.bidder.behaviourcontrol.BehaviorMultiplierEnum;
import optimax.bidder.behaviourcontrol.Behaviour;
import optimax.bidder.behaviourcontrol.BehaviourStrategyEnum;
import optimax.bidder.behaviourcontrol.DiferenceRelativeToAmount;

public class Safe extends Behaviour {

	public Safe() {
		super(BehaviorMultiplierEnum.SHY);
		this.currentStrategy = BehaviourStrategyEnum.SEEK;
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
		return (int) Math.round(bidder.opponentLastBid * this.intensity.getCodeMultiplier());
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

	private void evaluateWinning(int difSpen, DiferenceRelativeToAmount diferenceQuantity,
			DiferenceRelativeToAmount diferenceCash) {

		// how good is it? or is it good really? keep the same
		// winning and spending less. make no change please
		// winning but spending more. reevaluate
		if (difSpen < 0) {
			changeIntensityWinningAndSpendingMore(diferenceQuantity, diferenceCash);
		}
	}

	private void evaluateLosing(int difSpen, DiferenceRelativeToAmount diferenceQuantity) {
		if (difSpen > 0) {
			changeIntensityLosingAndSpendingLess(diferenceQuantity);
		} else if (difSpen < 0) { // i'm loosing and spending more. This is bad
			// go for a bait*
		} else {
			// if i'm loosing and spending the same amount, raize intensity.
			raizeIntensity();
		}
	}

	private void changeIntensityWinningAndSpendingMore(DiferenceRelativeToAmount diferenceQuantity,
			DiferenceRelativeToAmount diferenceCash) {

		if (diferenceQuantity.equals(DiferenceRelativeToAmount.SMALL)
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
