package kun.dev.springBootAngular.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

import kun.dev.springBootAngular.Domain.Property;
import kun.dev.springBootAngular.Domain.PropertyCard;

public interface PropertyService {
	Collection<Property> findAll();
	Property findById(String id);
	Collection<Property> findSale();
	Collection<Property> findSoldOrPurchased();
	void deleteById(String id);
	Property addNewProperty(Property property);
	Property update(Property property);
	Collection<Property> findByAddress(String address);
  Collection<PropertyCard> getPropertyCards();
  Collection<PropertyCard> getOpenHouseCards();
}
