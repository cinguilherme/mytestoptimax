package optimax.bidder.base;

import java.util.ArrayList;
import java.util.List;

import optimax.bidder.behaviourcontrol.Behaviour;
import optimax.bidder.behaviourcontrol.enums.DiferenceRelativeToAmountEnum;
import optimax.bidder.resultcontrol.ActionResult;

/**
 * Base Bidder is the base of all types of bidders.
 * 
 * @author cingu
 *
 */
public abstract class BaseBidder {

	private Behaviour behaviour;
	private List<ActionResult> actionResults;

	public OpponnetData opponentData;
	public SelfData data;

	public BaseBidder() {
		opponentData = new OpponnetData();
		data = new SelfData();
		actionResults = new ArrayList<ActionResult>();
	}

	/**
	 * 
	 * @return the diference from the opponent spenses to my own. Negative means I
	 *         spent more
	 */
	public int diferenceInSpense() {
		return opponentData.spentCash - data.spentCash;
	}

	/**
	 * 
	 * @return the diference from the opponent qauntity to my own. Negative means I
	 *         got more produts
	 */
	public int diferenceInQuantity() {
		return opponentData.quantity - data.quantity;
	}

	/**
	 * 
	 * @return DiferenceRelativeToAmount SMALL <= 20% > MODERATE <= 35% > or LARGE
	 */
	public DiferenceRelativeToAmountEnum difInCash() {

		if (data.cash > 0) {
			int evaluation = Math.abs(diferenceInSpense());
			double perc = ((double)evaluation / (double)data.cash) * 100;
			if (perc <= 20) {
				return DiferenceRelativeToAmountEnum.SMALL;
			} else if (perc <= 35) {
				return DiferenceRelativeToAmountEnum.MODERATE;
			} else {
				return DiferenceRelativeToAmountEnum.LARGE;
			}
		} else {
			return DiferenceRelativeToAmountEnum.LARGE;
		}
	}

	/**
	 * Determine how big is the diference in quantity gained? this diference should
	 * provide support to how wild or safe you can be in terms of agressive bidds.
	 * 
	 * rule: cash diference in relation to the total quantity so far
	 * 
	 * EX: quantity = 50; diference = -10 and opponent quantity = 40-> dif is 10 in
	 * relation to 90, its very small edge.
	 * 
	 * @return DiferenceRelativeToAmount SMALL <= 10% > MODERATE <= 20% > or LARGE
	 * @return
	 */
	public DiferenceRelativeToAmountEnum difInQuantity() {
		int evaluation = Math.abs(diferenceInQuantity());
		int perc = (evaluation / (data.quantity + opponentData.quantity)) * 100;
		if (perc <= 10) {
			return DiferenceRelativeToAmountEnum.SMALL;
		} else if (perc <= 20) {
			return DiferenceRelativeToAmountEnum.MODERATE;
		} else {
			return DiferenceRelativeToAmountEnum.LARGE;
		}
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public int pay(int value) {
		if (data.numberOfBids == 0) {
			data.inicialCash = data.cash;
		}
		data.numberOfBids++;

		if (data.canPay(value)) {
			data.cash -= value;
			data.spentCash += value;
			return value;
		}

		return 0;
	}

	/**
	 * based on the bidds, keep track of the current condition
	 * 
	 * @param own
	 * @param other
	 */
	public void evaluateResultsAndRegistre(int own, int other) {
		opponentData.lastBid = other;
		
		declareWinner(own, other);
		
		data.allBids.add(own);
		data.spentCash += own;

		opponentData.allBids.add(other);
		opponentData.spentCash += other;
	}

	private void declareWinner(int own, int other) {
		if (own < other) {
			getActionResults().add(ActionResult.LOSS);
			data.straightLossCounter++;
			opponentData.quantity += 2;
			opponentData.winingBids.add(other);
		} else if (own == other) {
			getActionResults().add(ActionResult.TIED);
			data.straightLossCounter = 0;
			data.quantity += 1;
			opponentData.quantity += 1;
		} else {
			getActionResults().add(ActionResult.WIN);
			data.straightLossCounter = 0;
			data.quantity += 2;
			data.winingBids.add(own);
		}
	}

	public Behaviour getBehaviour() {
		return behaviour;
	}

	public void setBehaviour(Behaviour behaviour) {
		this.behaviour = behaviour;
	}

	public List<ActionResult> getActionResults() {
		return actionResults;
	}

	public void setActionResults(List<ActionResult> actionResults) {
		this.actionResults = actionResults;
	}

}
