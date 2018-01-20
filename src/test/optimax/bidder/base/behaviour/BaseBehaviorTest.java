package test.optimax.bidder.base.behaviour;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import optimax.bidder.base.impl.BaseBidder;
import optimax.bidder.behaviourcontrol.Behaviour;
import optimax.bidder.behaviourcontrol.enums.BehaviorMultiplierEnum;

class BaseBehaviorTest {

	Behaviour behaveior;
	BaseBidder bidder;

	public BaseBehaviorTest() {
		behaveior = new Behaviour(BehaviorMultiplierEnum.SHY) {
		};
		
		bidder = new BaseBidder() {
		};
		
	}

	@Test
	void testBaseOpenner() {
		
		Assert.assertTrue(behaveior.opener(bidder) == 0);
	}
	
	@Test
	void testBaseRespond() {
		bidder.opponentData.lastBid = 10;
		
		Assert.assertTrue(behaveior.respond(bidder) == 12);
	}

	@Test
	void testBaseBait() {
		bidder.opponentData.lastBid = 40;
		bidder.opponentData.winingBids.add(30);
		bidder.opponentData.winingBids.add(32);
		bidder.opponentData.winingBids.add(31);
		bidder.opponentData.winingBids.add(28);
		
		Assert.assertTrue(behaveior.bait(bidder) == 0);

	}

	
}
