package optimax.bidder.behaviourcontrol;

public enum BehaviorMultiplierEnum {
	HOLD(0, "Hold"), SHY(1,"Shy"), MODERATE(1.5,"Moderate"), AGRESSIVE(2,"Agressive");

	double codeMultiplier;
	String description;
	
	private BehaviorMultiplierEnum(double code, String description) {
		this.codeMultiplier = code;
		this.description = description;
	}

	public double getCodeMultiplier() {
		return this.codeMultiplier;
	}
	
	public String getDescription() {
		return this.description;
	}

}
