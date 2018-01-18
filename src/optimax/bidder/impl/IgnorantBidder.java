package optimax.bidder.impl;

import optimax.bidder.BaseBidder;
import optimax.bidder.Bidder;

public class IgnorantBidder extends BaseBidder implements Bidder {

	@Override
	public void init(int quantity, int cash) {
		// TODO Auto-generated method stub

	}

	@Override
	public int placeBid() {
		// TODO Auto-generated method stub
		// evaluate my condidion, evaluate how much did my opponent already spent.
		// evaluate if i am wining or loosing
		// if completely unsure and wining, place 0
		// try to predict the minimal value of the oponent
		
		//get my current behaviour
		//get my current style
		
		int bid = this.getBehaviour().getMyNextBidBasedOnBehaviorAndConditions(this);
		this.pay(bid);
		return bid;
	}

	@Override
	public void bids(int own, int other) {
		evaluateResults(own, other);
	}

	

}
