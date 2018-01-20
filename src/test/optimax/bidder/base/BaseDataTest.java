package test.optimax.bidder.base;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import optimax.bidder.base.BaseData;

class BaseDataTest {

	BaseData data;

	public BaseDataTest() {
		data = new BaseData();
	}

	@Test
	void testAverageWinning() {
		Assert.assertTrue(data.averageWinningBid() == 0);
		
		data.winingBids.add(10);
		data.winingBids.add(10);
		Assert.assertTrue(data.averageWinningBid() == 10);
		
		data.winingBids.add(20);
		data.winingBids.add(20);
		Assert.assertTrue(data.averageWinningBid() == 60/4);
	}

	@Test
	void testAverageBidds() {
		Assert.assertTrue(data.averageBid() == 0);
		
		data.allBids.add(10);
		data.allBids.add(10);
		Assert.assertTrue(data.averageBid() == 10);
		
		data.allBids.add(20);
		data.allBids.add(20);
		Assert.assertTrue(data.averageBid() == 60/4);
	}
	
	@Test
	void testEscalatingWinnings() {
		Assert.assertTrue(data.isWinningBiddsEscalating() == false);
		
		data.winingBids.add(10);
		data.winingBids.add(11);
		data.winingBids.add(12);
		data.winingBids.add(13);
		
		Assert.assertTrue(data.isWinningBiddsEscalating() == false);
		
		data.winingBids.add(14);
		Assert.assertTrue(data.isWinningBiddsEscalating() == true);
		Assert.assertTrue(data.isWinningBiddsEscalating() == false);
		
	}
	
	@Test
	void testEscalatingAll() {
		Assert.assertTrue(data.isAllBidsEscalating() == false);
		
		data.allBids.add(10);
		data.allBids.add(11);
		data.allBids.add(12);
		data.allBids.add(13);
		
		Assert.assertTrue(data.isAllBidsEscalating() == false);
		
		data.allBids.add(14);
		Assert.assertTrue(data.isAllBidsEscalating() == true);
		Assert.assertTrue(data.isAllBidsEscalating() == false);
	}

}
