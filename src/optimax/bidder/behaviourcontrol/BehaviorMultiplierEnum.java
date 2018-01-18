package optimax.bidder.behaviourcontrol;

public enum BehaviorMultiplierEnum {
	SHY(1,"Shy"), MODERATE(2,"Moderate"), AGRESSIVE(3,"Agressive");

	int codeMultiplier;
	String description;
	
	private BehaviorMultiplierEnum(int code, String description) {
		this.codeMultiplier = code;
		this.description = description;
	}

	public int getCodeMultiplier() {
		return this.codeMultiplier;
	}
	
	public String getDescription() {
		return this.description;
	}

}
