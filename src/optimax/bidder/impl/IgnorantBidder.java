package optimax.bidder.impl;

import optimax.bidder.BaseBidder;
import optimax.bidder.Bidder;
import optimax.bidder.behaviourcontrol.impl.Safe;

public class IgnorantBidder extends BaseBidder implements Bidder {
	
	public IgnorantBidder() {
		this.setBehaviour(new Safe());
	}
	
	@Override
	public void init(int quantity, int cash) {
		this.quantity = quantity;
		this.cash = cash;
	}

	@Override
	public int placeBid() {
		// TODO Auto-generated method stub
		
		//the bidder has the data, the behavior is going to decide what to do.
		//if behavior not working, change behavior to a best match against the opponent behavior
		
		int bid = this.getBehaviour().getMyNextBidBasedOnBehaviorAndConditions(this);
		this.pay(bid);
		return bid;
	}

	@Override
	public void bids(int own, int other) {
		evaluateResults(own, other);
	}

	

}
