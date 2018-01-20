package test.optimax.bidder.base.behaviour;

import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.swing.plaf.basic.BasicArrowButton;

import org.junit.jupiter.api.Test;

import optimax.bidder.base.BaseBidder;
import optimax.bidder.behaviourcontrol.enums.BehaviourStrategyEnum;
import optimax.bidder.behaviourcontrol.impl.Safe;

class SafeTest {

	Safe behaviour;
	BaseBidder bidder;
	
	public SafeTest() {
		behaviour = new Safe();
		bidder = new BaseBidder() {
		};
	}
	
	private void basicBidderData() {
		bidder.data.cash = 100;
		bidder.data.winingBids.add(10);
		bidder.data.allBids.add(10);
		bidder.opponentData.allBids.add(8);
		bidder.opponentData.lastBid = 8;
	}

	@Test
	void testRespond() {
		basicBidderData();
		
		/*
		 * basic reponse is last bid + 1 * intensity(1.1)= 10
		 * speficic Safe response is 10 + 1 
		 * and a reavaluation of strategy, in this case it wont change
		 * */
		
		assertTrue(behaviour.respond(bidder) == 11);
	}
	
	@Test
	void testBaitChange() {
		basicBidderData();
		
		/*
		 * basic reponse is last bid + 1 * intensity(1.1)= 10
		 * speficic Safe response is 10 + 1 
		 * and a reavaluation of strategy, in this case it wont change
		 * */
		
		assertTrue(behaviour.respond(bidder) == 11);
		
		baitSetUp();
		
		/*
		 * basic reponse is last bid + 1 * intensity(1.1)= 28 + 1 == 29 * 1.1 == 32
		 * speficic Safe response is 32 + 1 
		 * 
		 * and a reavaluation of strategy, in this case will change to bait for detecting agressive escalation
		 * */
		assertTrue(behaviour.respond(bidder) == 33);
		
		assertTrue(behaviour.getCurrentStrategy() == BehaviourStrategyEnum.BAIT);
	}

	private void baitSetUp() {
		
		bidder.opponentData.winingBids.add(20);
		bidder.opponentData.winingBids.add(22);
		bidder.opponentData.winingBids.add(23);
		bidder.opponentData.winingBids.add(25);
		bidder.opponentData.winingBids.add(27);
		bidder.opponentData.winingBids.add(28);
		
		bidder.opponentData.allBids.add(20);
		bidder.opponentData.allBids.add(22);
		bidder.opponentData.allBids.add(23);
		bidder.opponentData.allBids.add(25);
		bidder.opponentData.allBids.add(27);
		bidder.opponentData.allBids.add(28);
		
		bidder.opponentData.lastBid = 28;
		
		bidder.data.allBids.add(13);
		bidder.data.allBids.add(14);
		bidder.data.allBids.add(15);
		bidder.data.allBids.add(16);
		bidder.data.allBids.add(17);
		bidder.data.allBids.add(18);
		
		bidder.data.winingBids.add(13);
		bidder.data.winingBids.add(14);
		bidder.data.winingBids.add(15);
		bidder.data.winingBids.add(16);
		bidder.data.winingBids.add(17);
		bidder.data.winingBids.add(18);
		
	}
	
}
