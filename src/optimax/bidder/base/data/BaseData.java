package optimax.bidder.base.data;

import java.util.ArrayList;
import java.util.List;

public class BaseData {

	public int quantity = 0;
	public int spentCash = 0;

	public List<Integer> allBids;
	public List<Integer> winingBids;

	private Integer allEscIndex = 0;
	private Integer winEscIndex = 0;

	public BaseData() {
		allBids = new ArrayList<Integer>();
		winingBids = new ArrayList<Integer>();
	}

	/**
	 * The average value of winning bidds
	 * 
	 * @return
	 */
	public Integer averageWinningBid() {
		return DataProcessingModule.average(winingBids);
	}

	/**
	 * The average value of all bidds
	 * 
	 * @return
	 */
	public Integer averageBid() {
		return DataProcessingModule.average(allBids);
	}

	/**
	 * Evaluate and return id the biddings are escalating
	 * 
	 * @return
	 */
	public boolean isWinningBiddsEscalating() {
		boolean res = DataProcessingModule.isListValueEscalating(winingBids, winEscIndex);
		if (res == true) {
			winEscIndex = winingBids.size() - 1;
		}
		return res;
	}

	/**
	 * Evaluate and return id the biddings are escalating
	 * 
	 * @return
	 */
	public boolean isAllBidsEscalating() {
		boolean res = DataProcessingModule.isListValueEscalating(allBids, allEscIndex);
		if (res == true) {
			allEscIndex = allBids.size() - 1;
		}
		return res;
	}

}
