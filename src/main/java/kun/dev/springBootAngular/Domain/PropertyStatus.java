package kun.dev.springBootAngular.Domain;

public enum PropertyStatus {
	SALE("Sale"),
	SOLD("Sold"),
	PURCHASED("Purchased");

	private final String name;

	PropertyStatus(final String name) {
		this.name = name;
	}

    @Override
    public String toString() {
        return name;
    }
}
