package optimax.auction;

import optimax.bidder.Bidder;
import optimax.bidder.base.impl.BaseBidder;

public class Auction {

	int numberOfProducts;
	int numberOfSell = 0;

	public Auction(int listSize) {
		numberOfProducts = listSize;
	}

	private void getNextSell(Bidder one, Bidder two) {
		if (numberOfProducts > 1) {
			int fr = one.placeBid();
			int sec = two.placeBid();
			one.bids(fr, sec);
			two.bids(sec, fr);
			System.out.println("sell number " + numberOfSell + " - one bid: " + fr + " and two bid: " + sec);
			numberOfProducts -= 2;
		}
	}

	public String executeActionAndDeclareWinner(Bidder one, Bidder two) {

		while (numberOfProducts >= 2) {
			numberOfSell++;
			getNextSell(one, two);
		}

		if (((BaseBidder) one).data.quantity > ((BaseBidder) two).data.quantity) {
			return "One";
		} else if (((BaseBidder) one).data.quantity < ((BaseBidder) two).data.quantity) {
			return "Two";
		} else {
			return "Tied";
		}

	}

}
