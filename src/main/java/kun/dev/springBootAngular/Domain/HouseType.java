package kun.dev.springBootAngular.Domain;

public enum HouseType {
	DETACHED_HOUSE("Detached House"),
	TOWN_HOUSE("Town House"),
	CONDO("Condo");

	private String shortName;

	HouseType(String shortName) {
		this.shortName = shortName;
	}

	public String getShortName() {
		return shortName;
	}

	@Override
	public String toString() {
		return shortName;
	}
}
