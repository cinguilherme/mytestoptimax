package test.optimax.bidder.base;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import optimax.bidder.impl.SillyBidder;

class SillyBidderTest {

	SillyBidder bidder;

	public SillyBidderTest() {
		bidder = new SillyBidder();
	}

	@Test
	void testInit() {
		bidder.init(10, 10);

		assertTrue(bidder.data.cash == 10);
		assertTrue(bidder.data.quantity == 10);
	}

	@Test
	void testPay() {
		bidder.data.cash = 10;
		assertTrue(bidder.pay(8) == 8);

		assertTrue(bidder.pay(1) == 1);

		assertTrue(bidder.pay(2) == 1);

		assertTrue(bidder.pay(1) == 0);

	}

	@Test
	void testPlaceBid() {

		bidder.data.cash = 100;

		// opnener
		assertTrue(bidder.placeBid() == 0);

		bidder.data.winingBids.add(10);
		bidder.opponentData.allBids.add(1);
		bidder.opponentData.lastBid = 1;

		/*
		 * response (1 + 1) * 1.1 = 2 bidder.getBehaviour().respond(bidder) + 1)
		 */

		assertTrue(bidder.placeBid() == 3);

	}

}
