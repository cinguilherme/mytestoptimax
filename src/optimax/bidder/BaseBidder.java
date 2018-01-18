package optimax.bidder;

import java.util.List;

import optimax.bidder.behaviourcontrol.Behaviour;
import optimax.bidder.behaviourcontrol.DiferenceRelativeToAmount;
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
	private int inicialCash = 0;
	
	protected int cash;

	public int opponnentQuantity = 0;
	public int opponnentCashSpent = 0;
	public int opponentLastBid = 0;
	public int numberOfBids = 0;
	public int quantity = 0;
	public int spentCash = 0;

	/**
	 * 
	 * @return the diference from the opponent spenses to my own. Negative means I spent more
	 */
	public int diferenceInSpense() {
		return opponnentCashSpent - spentCash;
	}
	
	/**
	 * 
	 * @return the diference from the opponent qauntity to my own. Negative means I got more produts
	 */
	public int diferenceInQuantity() {
		return opponnentQuantity - quantity;
	}
	
	/**
	 * determine how big is the diference in cash spent? 
	 * this diference should provide support to how wild or safe you can be in terms of agressive bidds.
	 * 
	 * rule: cash diference in relation to the remainder cash
	 * 
	 * EX: cash = 50; diference = -10 -> dif is 60 how bad is it? 60 is 20% higher than 50. It should be SMALL
	 * 
	 * @return DiferenceRelativeToAmount SMALL <= 20% > MODERATE <= 35% > or LARGE
	 */
	public DiferenceRelativeToAmount difInCash() {
		int evaluation = cash - diferenceInSpense();
		int perc = (evaluation/cash) * 100;
		if(perc <= 20) {
			return DiferenceRelativeToAmount.SMALL;
		} else if(perc <= 35) {
			return DiferenceRelativeToAmount.MODERATE;
		} else {
			return DiferenceRelativeToAmount.LARGE;
		}
	}
	
	/**
	 * Determine how big is the diference in quantity gained? 
	 * this diference should provide support to how wild or safe you can be in terms of agressive bidds.
	 * 
	 * rule: cash diference in relation to the total quantity so far
	 * 
	 * EX: quantity = 50; diference = -10 and opponent quantity = 40-> dif is 10 in relation to 90, its very small edge.
	 * 
	 * @return DiferenceRelativeToAmount SMALL <= 10% > MODERATE <= 20% > or LARGE
	 * @return
	 */
	public DiferenceRelativeToAmount difInQuantity() {
		int evaluation = Math.abs(diferenceInQuantity());
		int perc = (evaluation/(quantity+opponnentQuantity)) * 100;
		if(perc <= 10) {
			return DiferenceRelativeToAmount.SMALL;
		} else if(perc <= 20) {
			return DiferenceRelativeToAmount.MODERATE;
		} else {
			return DiferenceRelativeToAmount.LARGE;
		}
	}
	
	public boolean pay(int value) {
		
		if (numberOfBids == 0) {
			inicialCash = cash;
		}
		
		numberOfBids++;
		
		if (cash >= value) {
			cash -= value;
			spentCash += value;
		} else {
			return false;
		}
		return true;
	}

	/**
	 * based on the bidds, keep track of the current condition
	 * 
	 * @param own
	 * @param other
	 */
	public void evaluateResults(int own, int other) {
		opponentLastBid = other;
		if (own < other) {
			this.getActionResults().add(ActionResult.LOSS);
			this.opponnentQuantity += 2;
		} else if (own == other) {
			this.getActionResults().add(ActionResult.TIED);
			this.quantity += 1;
			this.opponnentQuantity += 1;
		} else {
			this.getActionResults().add(ActionResult.WIN);
			this.quantity += 2;
		}
		this.opponnentCashSpent += other;
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
