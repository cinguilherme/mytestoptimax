package optimax.auction;

import optimax.bidder.Bidder;
import optimax.bidder.base.BaseBidder;

public class Auction {

	int numberOfProducts;

	public Auction(int listSize) {
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
		
		if(((BaseBidder)one).data.quantity > ((BaseBidder)two).data.quantity) {
			return "One";
		} else if(((BaseBidder)one).data.quantity < ((BaseBidder)two).data.quantity) {
			return "Two";
		} else {
			return "Tied";
		}
		
		
		
	}

}
