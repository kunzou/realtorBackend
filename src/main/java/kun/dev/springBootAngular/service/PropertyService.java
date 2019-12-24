package kun.dev.springBootAngular.service;

import kun.dev.springBootAngular.Domain.Property;
import kun.dev.springBootAngular.repository.PropertyRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class PropertyService {
  private GeocodeService geocodeService;
  private PropertyRepository repository;

  public PropertyService(GeocodeService geocodeService, PropertyRepository repository) {
    this.geocodeService = geocodeService;
    this.repository = repository;
  }

  public Collection<Property> findAll() {
    return repository.findAll();
  }

  public Optional<Property> findById(String id) {
    return repository.findById(id);
  }

  public Collection<Property> findSale() {
    return repository.findSaleProperties();
  }

  public Collection<Property> findSoldOrPurchased() {
    return repository.findSoldOrPurchasedProperties();
  }

  public void deleteById(String id) {
    repository.deleteById(id);
  }

  public Property addNewProperty(Property property) {
    property.setVersion(1L);
    updateGeocode(property);
    return repository.save(property);
  }

  public Property update(Property property) {
    Property persistedProperty = repository.findById(property.getId()).orElseThrow(RuntimeException::new); //todo error handling

//    if(persistedProperty.getVersion().equals(property.getVersion())) {
      updateGeocode(property);
      property.setVersion(property.getVersion()+1);
      property = repository.save(property);
//    } else {
//      throw new RuntimeException("Oops, refresh the page please"); //todo error handling
//    }

    return property;
  }

  public Collection<Property> findByAddress(String address) {
    String searchTerm = address.replace(" ", "").replaceAll("[A-Z|a-z|0-9]", "$0.*?");
    return repository.findByAddress(searchTerm);
  }


  void updateGeocode(Property property) {
    if(property.getId() == null || !StringUtils.equals(repository.findById(property.getId()).map(Property::getAddress).orElse(null), property.getAddress())) {
      property.setLocation(geocodeService.getGeocode(property.getAddress()));
    }
  }
}
