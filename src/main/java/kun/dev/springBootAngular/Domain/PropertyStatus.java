package kun.dev.springBootAngular.Domain;

import com.fasterxml.jackson.annotation.JsonProperty;

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
