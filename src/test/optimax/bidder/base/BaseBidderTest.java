package test.optimax.bidder.base;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import optimax.bidder.base.BaseBidder;

class BaseBidderTest {

	BaseBidder bidder;
	
	public BaseBidderTest() {
		bidder = new BaseBidder() {
		};
	}
	
	@Test
	void testEvaluateResultsAndRegistre() {
		int own = 100;
		int other = 80;
		bidder.evaluateResultsAndRegistre(own, other);
		
		Assert.assertTrue(bidder.opponentData.lastBid == other);
		Assert.assertTrue(bidder.opponentData.allBids.size() == 1);
		Assert.assertTrue(bidder.opponentData.winingBids.size() == 0);
		Assert.assertTrue(bidder.opponentData.spentCash == other);
		
		Assert.assertTrue(bidder.data.allBids.size() == 1);
		Assert.assertTrue(bidder.data.winingBids.size() == 1);
		Assert.assertTrue(bidder.data.spentCash == own);
	}
	
	void testDiferenceInSpense() {
		
	}
	
	void testDiferenceInQuantity() {
		
	}
	
	
}
