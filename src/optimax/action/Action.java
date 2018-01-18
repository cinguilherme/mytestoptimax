package optimax.action;

import optimax.bidder.BaseBidder;
import optimax.bidder.Bidder;

public class Action {

	int numberOfProducts;

	public Action(int listSize) {
		numberOfProducts = listSize;
	}

	
	private void getNextSell(Bidder one, Bidder two) {
		if(numberOfProducts > 2) {
			int fr = one.placeBid();
			int sec = two.placeBid();
			one.bids(fr, sec);
			two.bids(sec, fr);
		}
	}
	
	public String executeActionAndDeclareWinner(Bidder one, Bidder two) {
		
		while(numberOfProducts >= 2) {
			getNextSell(one, two);
		}
		
		if(((BaseBidder)one).quantity > ((BaseBidder)two).quantity) {
			return "One";
		} else if(((BaseBidder)one).quantity < ((BaseBidder)two).quantity) {
			return "Two";
		} else {
			return "Tied";
		}
		
		
		
	}

}
