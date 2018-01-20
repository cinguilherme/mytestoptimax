package optimax.bidder.base.data;

import java.util.ArrayList;
import java.util.List;

import optimax.bidder.base.data.intel.DataProcessingModule;

public class BaseData {

	public int quantity = 0;
	public int spentCash = 0;

	public List<Integer> allBids;
	public List<Integer> winingBids;

	private Integer allEscIndex = 0;
	private Integer winEscIndex = 0;
	
	private List<BiddingData> biddsList;

	public BaseData() {
		allBids = new ArrayList<Integer>();
		winingBids = new ArrayList<Integer>();
		biddsList = new ArrayList<BiddingData>();
	}

	public void saveBiddData(int own, int other) {
		BiddingData newBidd = new BiddingData();
		newBidd.setOtherValue(other);
		newBidd.setOwnValue(own);
		newBidd.setTurn(allBids.size());
		newBidd.setResult(own - other);
		biddsList.add(newBidd);
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
	
	public boolean checkLosingCycle() {
		return DataProcessingModule.isLossCycle(biddsList);
	}

}
