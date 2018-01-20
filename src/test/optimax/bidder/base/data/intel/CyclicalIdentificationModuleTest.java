package test.optimax.bidder.base.data.intel;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import optimax.bidder.base.data.BiddingData;
import optimax.bidder.base.data.intel.CyclicalIdentificationModule;

public class CyclicalIdentificationModuleTest {

	private List<BiddingData> allBidding;

	public CyclicalIdentificationModuleTest() {
		allBidding = new ArrayList<BiddingData>();
	}

	@Test
	void testLosingCycle() {
		setupPositiveResult();

		assertTrue(CyclicalIdentificationModule.isLossCycle(allBidding));

		setupNegativeResult();
	}

	private void setupNegativeResult() {
		
		for (int i = 0; i < 10; i++) {

			BiddingData dat = new BiddingData();
			if (i % 4 == 0 || i % 4 == 1) {
				dat.setOwnValue(i);
				dat.setOtherValue(i + 2);
				dat.setResult(-2);
			} else {
				dat.setOwnValue(i + 2);
				dat.setOtherValue(i + 1);
				dat.setResult(1);
			}
			dat.setTurn(i + 1);

			allBidding.add(dat);
		}

	}

	private void setupPositiveResult() {
		for (int i = 0; i < 10; i++) {

			BiddingData dat = new BiddingData();
			if (i % 4 == 0 || i % 4 == 1) {
				dat.setOwnValue(i);
				dat.setOtherValue(i + 2);
				dat.setResult(-2);
			} else {
				dat.setOwnValue(i + 2);
				dat.setOtherValue(i + 1);
				dat.setResult(1);
			}
			dat.setTurn(i + 1);

			allBidding.add(dat);
		}

	}

}
