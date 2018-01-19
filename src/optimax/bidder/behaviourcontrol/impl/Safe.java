package optimax.bidder.behaviourcontrol.impl;

import optimax.bidder.base.BaseBidder;
import optimax.bidder.behaviourcontrol.Behaviour;
import optimax.bidder.behaviourcontrol.enums.BehaviorMultiplierEnum;
import optimax.bidder.behaviourcontrol.enums.BehaviourStrategyEnum;
import optimax.bidder.behaviourcontrol.enums.DiferenceRelativeToAmountEnum;

public class Safe extends Behaviour {

	private int relativeModifier = 2;
	private int lossInRow = 0;

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

		reEvaluateStrategy(bidder);
		
		int max = evaluateMyMaxBid(bidder);

		//chaos play, i'm getting readed
		if (evaluateShock(bidder)) {
			return 0;
		} else {
			int intentBid = (int) Math.round(
					(bidder.opponentData.averageWinningBid() + this.relativeModifier) * this.intensity.getCodeMultiplier());

			return intentBid <= max ? intentBid : max;
		}
	}

	/**
	 * sets straight loss counter back to 0 because the next bid will be a loss.
	 * @param bidder
	 * @return
	 */
	private boolean evaluateShock(BaseBidder bidder) {
		if (bidder.data.straightLossCounter > 2) {
			bidder.data.straightLossCounter = 0;
			return true;
		}
		return false;
	}

	/**
	 * Max bid should not be over 20% on inicial cash pool while it is over 50%
	 * after that the max should be 20% of the remainder
	 * 
	 * @param bidder
	 * @return
	 */
	private int evaluateMyMaxBid(BaseBidder bidder) {
		int totalInicial = bidder.data.spentCash + bidder.data.cash;
		if (bidder.data.spentCash > totalInicial / 2) {
			return bidder.data.cash / 10;
		}
		return (totalInicial / 10) * 2;
	}

	@Override
	public void reEvaluateStrategy(BaseBidder bidder) {

		int winning = bidder.diferenceInQuantity();
		int difSpen = bidder.diferenceInSpense();
		DiferenceRelativeToAmountEnum diferenceQuantity = bidder.difInQuantity();
		DiferenceRelativeToAmountEnum diferenceCash = bidder.difInCash();

		System.out.println("reEvaluateStrategy");

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
	private void evaluateWinning(int difSpen, DiferenceRelativeToAmountEnum diferenceQuantity,
			DiferenceRelativeToAmountEnum diferenceCash) {

		if (difSpen < 0) {
			changeIntensityWinningAndSpendingMore(diferenceQuantity, diferenceCash);
		} else {
			// winning and spending less? outher bot will try to raise;
			this.relativeModifier += 2;
		}
	}

	/**
	 * i'm loosing and spending more? This is bad if i'm loosing and spending the
	 * same amount? raize intensity.
	 * 
	 * @param difSpen
	 * @param diferenceQuantity
	 */
	private void evaluateLosing(int difSpen, DiferenceRelativeToAmountEnum diferenceQuantity) {
		if (difSpen > 0) {
			changeIntensityLosingAndSpendingLess(diferenceQuantity);
		} else if (difSpen < 0) {
			// dont know what to do here.. maybe go for sky straight way
			// out?
			changeIntensity(BehaviorMultiplierEnum.HOLD);
		} else {
			// if i'm loosing and spending the same amount, raize intensity.
			raizeIntensity();
		}
	}

	/**
	 * In case winning but spending much more?
	 * 
	 * @param diferenceQuantity
	 * @param diferenceCash
	 */
	private void changeIntensityWinningAndSpendingMore(DiferenceRelativeToAmountEnum diferenceQuantity,
			DiferenceRelativeToAmountEnum diferenceCash) {

		System.out.println("winning and spending more");

		if (diferenceQuantity.equals(DiferenceRelativeToAmountEnum.MODERATE)
				&& diferenceCash.equals(DiferenceRelativeToAmountEnum.LARGE)) {
			System.out.println("MUCH more");
			lowerIntensity();
			relativeModifier -= 2;
		} else {
			relativeModifier += 2;
		}

	}

	private void changeIntensityLosingAndSpendingLess(DiferenceRelativeToAmountEnum diferenceQuantity) {
		System.out.println("loosing and spending less");
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
