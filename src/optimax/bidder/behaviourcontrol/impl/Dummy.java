package optimax.bidder.behaviourcontrol.impl;

import optimax.bidder.behaviourcontrol.Behaviour;
import optimax.bidder.behaviourcontrol.enums.BehaviorMultiplierEnum;
import optimax.bidder.behaviourcontrol.enums.BehaviourStrategyEnum;

public class Dummy extends Behaviour {

	public Dummy() {
		super(BehaviorMultiplierEnum.SHY);
		this.currentStrategy = BehaviourStrategyEnum.TRADE;
	}

}
