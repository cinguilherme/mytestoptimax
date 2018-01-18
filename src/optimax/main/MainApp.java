package optimax.main;

import optimax.action.Action;
import optimax.bidder.Bidder;
import optimax.bidder.impl.SillyBidder;

public class MainApp {

	public static void main(String[] args) {
		
		Bidder one = new SillyBidder();
		Bidder two = new SillyBidder();
		
		one.init(0, 100);
		two.init(0, 120);
		
		Action action = new Action(100);
		
		System.out.println(action.executeActionAndDeclareWinner(one, two));
		
	}
	
}
