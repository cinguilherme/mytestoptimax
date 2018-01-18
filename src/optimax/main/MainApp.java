package optimax.main;

import optimax.action.Action;
import optimax.bidder.Bidder;
import optimax.bidder.impl.IgnorantBidder;

public class MainApp {

	public static void main(String[] args) {
		
		Bidder one = new IgnorantBidder();
		Bidder two = new IgnorantBidder();
		
		one.init(0, 100);
		two.init(0, 120);
		
		Action action = new Action(100);
		
		System.out.println(action.executeActionAndDeclareWinner(one, two));
		
	}
	
}
