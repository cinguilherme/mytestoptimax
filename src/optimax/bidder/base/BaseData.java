package optimax.bidder.base;

import java.util.ArrayList;
import java.util.List;

public class BaseData {

	public int quantity = 0;
	public int spentCash = 0;
	
	public List<Integer> allBids;
	public List<Integer> winingBids;
	
	public BaseData() {
		allBids = new ArrayList<Integer>();
		winingBids = new ArrayList<Integer>();
	}
	
	public Integer averageWinningBid() {
		return average(winingBids);
	}

	public Integer averageBid() {
		return average(allBids);
	}

	private Integer average(List<Integer> bids) {
		int av = 0;
		for (Integer bid : bids) {
			av += bid;
		}
		return Math.round(av/bids.size());
	}
	
}
