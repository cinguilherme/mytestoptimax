package optimax.bidder.base;

public class SelfData extends BaseData{

	protected int inicialCash = 0;

	public int cash;

	public int quantity = 0;
	
	public int numberOfBids = 0;
	
	public int straightLossCounter = 0;
	
	public boolean canPay(int value) {
		if(cash >= value) {
			return true;
		}
		return false;
	}
	
}
