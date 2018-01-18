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

	private List<Behaviour> behaviours;
	private List<ActionResult> actionResults;

	protected int opponnentQuantity = 0;
	protected int opponnentCashSpent = 0;
	protected int cash;

	public int quantity = 0;
	public int spentCash = 0;

	public boolean payIfCan(int value) {
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

	public List<Behaviour> getBehaviours() {
		return behaviours;
	}

	public void setBehaviours(List<Behaviour> behaviours) {
		this.behaviours = behaviours;
	}

	public List<ActionResult> getActionResults() {
		return actionResults;
	}

	public void setActionResults(List<ActionResult> actionResults) {
		this.actionResults = actionResults;
	}

}
