package optimax.bidder;

import java.util.List;

import optimax.bidder.behaviourcontrol.Behaviour;
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
	
	protected int cash;

	public int opponnentQuantity = 0;
	public int opponnentCashSpent = 0;
	public int opponentLastBid = 0;
	public int numberOfBids = 0;
	public int quantity = 0;
	public int spentCash = 0;

	public boolean pay(int value) {
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
