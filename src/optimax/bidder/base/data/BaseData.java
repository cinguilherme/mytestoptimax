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
		return average(winingBids);
	}

	/**
	 * The average value of all bidds
	 * 
	 * @return
	 */
	public Integer averageBid() {
		return average(allBids);
	}

	private Integer average(List<Integer> bids) {
		if (bids.size() < 1) {
			return 0;
		}

		int av = 0;
		for (Integer bid : bids) {
			av += bid;
		}
		return Math.round(av / bids.size());
	}

	/**
	 * Evaluate and return id the biddings are escalating
	 * 
	 * @return
	 */
	public boolean isWinningBiddsEscalating() {
		boolean res = isListValueEscalating(winingBids, winEscIndex);
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
		boolean res = isListValueEscalating(allBids, allEscIndex);
		if (res == true) {
			allEscIndex = allBids.size() - 1;
		}
		return res;
	}

	/**
	 * Check if values from a start index -> are escalating
	 * 
	 * @param list
	 * @param indexStart
	 * @return
	 */
	private boolean isListValueEscalating(List<Integer> list, Integer indexStart) {
		boolean result = false;
		if (list.size() - indexStart >= 5) {
			for (int i = indexStart; i < list.size() - 1; i++) {
				if (list.get(i) > list.get(i + 1)) {
					result = false;
				}
			}
			indexStart = list.size() - 1;
			result = true;
		}
		return result;
	}

}
