package optimax.bidder.base.data;

public class BiddingData {

	private int turn;
	private int ownValue;
	private int otherValue;

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

}
