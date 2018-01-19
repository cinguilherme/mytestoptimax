package optimax.bidder.impl;

import optimax.bidder.Bidder;
import optimax.bidder.base.BaseBidder;
import optimax.bidder.behaviourcontrol.impl.Dummy;

public class NoGoodBidder extends BaseBidder implements Bidder{

	public NoGoodBidder() {
		setBehaviour(new Dummy());
	}
	
	@Override
	public void init(int quantity, int cash) {
		data.quantity = quantity;
		data.cash = cash;
	}

	@Override
	public int placeBid() {
		int value = getBehaviour().getMyNextBidBasedOnBehaviorAndConditions(this);
		value = pay(value);
		return value;
	}

	@Override
	public void bids(int own, int other) {
		evaluateResultsAndRegistre(own, other);
	}

}
