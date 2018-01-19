package optimax.main;

import optimax.auction.Auction;
import optimax.bidder.Bidder;
import optimax.bidder.impl.NoGoodBidder;
import optimax.bidder.impl.SillyBidder;

public class MainApp {

	public static void main(String[] args) {
		
		Bidder one = new SillyBidder();
		Bidder two = new NoGoodBidder();
		
		one.init(0, 1000);
		
		two.init(0, 1000);
		
		Auction action = new Auction(100);
		
		System.out.println(action.executeActionAndDeclareWinner(one, two));
		
	}
	
}
