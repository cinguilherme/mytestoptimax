package optimax.bidder.impl;

import optimax.bidder.Bidder;
import optimax.bidder.base.BaseBidder;
import optimax.bidder.behaviourcontrol.impl.Safe;

public class SillyBidder extends BaseBidder implements Bidder {

	public SillyBidder() {
		this.setBehaviour(new Safe());
	}

	@Override
	public void init(int quantity, int cash) {
		this.data.quantity = quantity;
		this.data.cash = cash;
	}

	@Override
	public int placeBid() {
		int bid = getBehaviour().getMyNextBidBasedOnBehaviorAndConditions(this);
		bid = pay(bid);
		return bid;
	}
	
	/**
	 * pay method for the Silly Bidder will pay as much as he has.
	 */
	@Override
	public int pay(int value) {
		data.numberOfBids++;
		while(data.canPay(value) == false) {
			value--;
		}
		data.cash -= value;
		data.spentCash += value;
		return value;
	}

	@Override
	public void bids(int own, int other) {
		evaluateResultsAndRegistre(own, other);
	}

}
