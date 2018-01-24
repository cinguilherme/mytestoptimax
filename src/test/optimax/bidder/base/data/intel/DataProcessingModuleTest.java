package test.optimax.bidder.base.data.intel;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import optimax.bidder.base.data.intel.DataProcessingModule;

class DataProcessingModuleTest {

	List<Integer> bids;
	
	public DataProcessingModuleTest() {
		bids = Arrays.asList(1,2,3,4,5,6);
	}
	
	@Test
	void testAverage() {
		Integer average = DataProcessingModule.average(bids);
		assertTrue(average.equals(3));
	}

}
