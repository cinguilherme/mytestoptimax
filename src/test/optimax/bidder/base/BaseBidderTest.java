package test.optimax.bidder.base;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import optimax.bidder.base.impl.BaseBidder;
import optimax.bidder.behaviourcontrol.enums.DiferenceRelativeToAmountEnum;

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
	
	@Test
	void testDiferenceInSpense() {
		int own = 100;
		int other = 80;
		bidder.evaluateResultsAndRegistre(own, other);
		bidder.evaluateResultsAndRegistre(own, other);
		
		Assert.assertTrue(bidder.diferenceInSpense() == -40);
		
		bidder.evaluateResultsAndRegistre(own, other);
		bidder.evaluateResultsAndRegistre(own, other);
		
		Assert.assertTrue(bidder.diferenceInSpense() == -80);
	}
	
	@Test
	void testDiferenceInQuantity() {
		int own = 100;
		int other = 80;
		bidder.evaluateResultsAndRegistre(own, other);
		bidder.evaluateResultsAndRegistre(own, other);
		
		Assert.assertTrue(bidder.diferenceInQuantity() == -4);
		
		bidder.evaluateResultsAndRegistre(own, other);
		bidder.evaluateResultsAndRegistre(own, other);
		
		Assert.assertTrue(bidder.diferenceInQuantity() == -8);
	}
	
	@Test
	void testPay() {
		int val = 100;
		bidder.data.cash = 120;
		
		Assert.assertTrue(bidder.pay(100) == 100);
		Assert.assertTrue(bidder.data.cash == 20);
		
		Assert.assertTrue(bidder.pay(100) == 0);
		Assert.assertTrue(bidder.data.cash == 20);
		
	}
	
	@Test
	void testDiferenceCashSpentRelativeToOpponent() {
		bidder.data.spentCash = 30;
		bidder.opponentData.spentCash = 15;
		
		Assert.assertTrue(bidder.difInCash().equals(DiferenceRelativeToAmountEnum.MODERATE));
		
		bidder.data.spentCash = 500;
		bidder.opponentData.spentCash = 480;
		Assert.assertTrue(bidder.difInCash().equals(DiferenceRelativeToAmountEnum.SMALL));
		
		
		bidder.data.spentCash = 430;
		bidder.opponentData.spentCash = 480;
		Assert.assertTrue(bidder.difInCash().equals(DiferenceRelativeToAmountEnum.SMALL));
		
		bidder.data.spentCash = 400;
		bidder.opponentData.spentCash = 480;
		Assert.assertTrue(bidder.difInCash().equals(DiferenceRelativeToAmountEnum.SMALL));
		
	}
	
	@Test
	void testDiferenceQuantityRelativeToOpponent() {
		bidder.data.quantity = 30;
		bidder.opponentData.quantity = 15;
		
		Assert.assertTrue(bidder.difInQuantity().equals(DiferenceRelativeToAmountEnum.LARGE));
		
		bidder.data.quantity = 1;
		bidder.opponentData.quantity = 2;
		Assert.assertTrue(bidder.difInQuantity().equals(DiferenceRelativeToAmountEnum.LARGE));
		
		
		bidder.data.quantity = 5;
		bidder.opponentData.quantity = 7;
		Assert.assertTrue(bidder.difInQuantity().equals(DiferenceRelativeToAmountEnum.MODERATE));
		
		bidder.data.quantity = 20;
		bidder.opponentData.quantity = 10;
		Assert.assertTrue(bidder.difInQuantity().equals(DiferenceRelativeToAmountEnum.LARGE));
		
	}
	
	
}
