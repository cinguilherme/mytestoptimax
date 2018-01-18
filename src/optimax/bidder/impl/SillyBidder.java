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

		// the bidder has the data, the behavior is going to decide what to do.
		// if behavior not working, change behavior to a best match against the opponent
		// behavior

		int bid = this.getBehaviour().getMyNextBidBasedOnBehaviorAndConditions(this);
		this.pay(bid);
		return bid;
	}

	@Override
	public void bids(int own, int other) {
		evaluateResultsAndRegistre(own, other);
	}

}
