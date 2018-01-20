package optimax.bidder.behaviourcontrol.enums;

public enum BehaviorMultiplierEnum {
	HOLD(0, "Hold"), SHY(1.1,"Shy"), MODERATE(1.2,"Moderate"), AGRESSIVE(1.3,"Agressive");

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
