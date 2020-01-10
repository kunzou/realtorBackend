package kun.dev.springBootAngular.service;

import java.util.Collection;

import kun.dev.springBootAngular.Domain.Property;

public interface PropertyService {
	Collection<Property> findAll();
	Property findById(String id);
	Collection<Property> findSale();
	Collection<Property> findSoldOrPurchased();
	void deleteById(String id);
	Property addNewProperty(Property property);
	Property update(Property property);
	Collection<Property> findByAddress(String address);
}
