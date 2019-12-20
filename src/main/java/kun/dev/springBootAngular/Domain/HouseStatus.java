package kun.dev.springBootAngular.Domain;

public enum HouseStatus {
  ON_MARKET("On market"),
  SOLD("Sold");

  private String shortName;

  HouseStatus(String shortName) {
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
