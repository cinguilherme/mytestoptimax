package test.optimax.bidder.base.behaviour;

import org.junit.jupiter.api.Test;

import optimax.bidder.base.BaseBidder;
import optimax.bidder.behaviourcontrol.impl.Safe;

class SafeTest {

	Safe behaviour;
	BaseBidder bidder;
	
	public SafeTest() {
		behaviour = new Safe();
		bidder = new BaseBidder() {
		};
	}

	@Test
	void testRespond() {
		
		
		behaviour.respond(bidder);
	}
	
}
