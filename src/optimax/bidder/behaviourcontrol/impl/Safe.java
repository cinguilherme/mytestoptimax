package optimax.bidder.behaviourcontrol.impl;

import optimax.bidder.base.BaseBidder;
import optimax.bidder.behaviourcontrol.Behaviour;
import optimax.bidder.behaviourcontrol.enums.BehaviorMultiplierEnum;
import optimax.bidder.behaviourcontrol.enums.BehaviourStrategyEnum;
import optimax.bidder.behaviourcontrol.enums.DiferenceRelativeToAmountEnum;

public class Safe extends Behaviour {

	public Safe() {
		super(BehaviorMultiplierEnum.SHY);
		this.currentStrategy = BehaviourStrategyEnum.TRADE;
	}

	public Safe(BehaviorMultiplierEnum multiplier, BehaviourStrategyEnum strategy) {
		super(multiplier);
		this.currentStrategy = strategy;
	}

	/**
	 * Adjustment of +1 to the already complex calculations of the base respond.
	 */
	@Override
	public int respond(BaseBidder bidder) {
		reEvaluateStrategy(bidder);
		return super.respond(bidder) + 1;
	}

	/**
	 * Core method of a Implementation
	 */
	@Override
	protected void reEvaluateStrategy(BaseBidder bidder) {

		System.out.println("reEvaluateStrategy");

		int winning = bidder.diferenceInQuantity();
		int difSpen = bidder.diferenceInSpense();
		DiferenceRelativeToAmountEnum diferenceQuantity = bidder.difInQuantity();
		DiferenceRelativeToAmountEnum diferenceCash = bidder.difInCash();

		if (currentStrategy.equals(BehaviourStrategyEnum.RESPOND)) {
			if (isExpensesScalating(bidder)) {
				System.out.println("change to bait");
				currentStrategy = BehaviourStrategyEnum.BAIT;
			}
		} else {
			System.out.println("go back to response");
			currentStrategy = BehaviourStrategyEnum.RESPOND;
		}


		// win or tied
		if (winning <= 0) {
			evaluateWinning(difSpen, diferenceQuantity, diferenceCash);
		} else {
			evaluateLosing(difSpen, diferenceQuantity);
		}
	}

	/**
	 * Is my winning bidds goiing up non stop?
	 * @param bidder
	 * @return
	 */
	private boolean isExpensesScalating(BaseBidder bidder) {

		// it is safe to calculate if the expenses are escalating
		if (bidder.data.allBids.size() > 10 && bidder.data.isWinningBiddsEscalating()) {
			return true;
		}
		return false;
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

		if (difSpen < 0 && !diferenceQuantity.equals(DiferenceRelativeToAmountEnum.SMALL)) {
			changeIntensityWinningAndSpendingMore(diferenceQuantity, diferenceCash);
		} else if (difSpen >= 0 && diferenceQuantity.equals(DiferenceRelativeToAmountEnum.SMALL)) {
			raizeIntensity();
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
