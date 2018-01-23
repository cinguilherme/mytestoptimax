package optimax.bidder.base.data;

import java.time.LocalTime;
import java.time.ZoneId;

public class BiddingData {

	private int turn;
	private int ownValue;
	private int otherValue;
	private int result;
	private LocalTime timeInBerlin;

	public BiddingData() {
		ZoneId zone = ZoneId.of("Europe/Berlin");
		LocalTime time = LocalTime.now(zone);
		timeInBerlin = time;
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public int getOwnValue() {
		return ownValue;
	}

	public void setOwnValue(int ownValue) {
		this.ownValue = ownValue;
	}

	public int getOtherValue() {
		return otherValue;
	}

	public void setOtherValue(int otherValue) {
		this.otherValue = otherValue;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public LocalTime getTimeInBerlin() {
		return timeInBerlin;
	}
}
