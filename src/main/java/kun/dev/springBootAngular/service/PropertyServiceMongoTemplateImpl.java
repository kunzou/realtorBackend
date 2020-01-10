package kun.dev.springBootAngular.service;

import java.util.Collection;
import java.util.Optional;

import kun.dev.springBootAngular.Domain.Property;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
@Primary
public class PropertyServiceMongoTemplateImpl implements PropertyService {
  private GeocodeService geocodeService;
  private MongoTemplate mongoTemplate;

  public PropertyServiceMongoTemplateImpl(GeocodeService geocodeService, MongoTemplate mongoTemplate) {
    this.geocodeService = geocodeService;
    this.mongoTemplate = mongoTemplate;
  }

  @Override
  public Collection<Property> findAll() {
    return mongoTemplate.findAll(Property.class);
  }

  @Override
  public Property findById(String id) {
    return mongoTemplate.findOne(Query.query(Criteria.where("id").is(id)), Property.class);
  }

  @Override
  public Collection<Property> findSale() {
    Query query = new Query();
    query.addCriteria(Criteria.where("propertyStatus").is("Sale"));
    query.addCriteria(Criteria.where("hide").is(false));
    return mongoTemplate.find(query, Property.class);
  }

  @Override
  public Collection<Property> findSoldOrPurchased() {
    Query query = new Query();
    query.addCriteria(Criteria.where("propertyStatus").in("Sold", "Purchased"));
    query.addCriteria(Criteria.where("hide").is(false));
    return mongoTemplate.find(query, Property.class);
  }

  @Override
  public void deleteById(String id) {
    mongoTemplate.remove(new Query(Criteria.where("id").is(id)), Property.class);
  }

  @Override
  public Property addNewProperty(Property property) {
    property.setVersion(1L);
    updateGeocode(property);
    return mongoTemplate.save(property);
  }

  @Override
  public Property update(Property property) {
    Property persistedProperty = findById(property.getId()); //todo error handling

//    if(persistedProperty.getVersion().equals(property.getVersion())) {
      updateGeocode(property);
      property.setVersion(property.getVersion()+1);
//      property = repository.save(property);
      property = mongoTemplate.save(property);
//    } else {
//      throw new RuntimeException("Oops, refresh the page please"); //todo error handling
//    }

    return property;
  }

  @Override
  public Collection<Property> findByAddress(String address) {
    String searchTerm = address.replace(" ", "").replaceAll("[A-Z|a-z|0-9]", "$0.*?");
    return mongoTemplate.find(new Query(Criteria.where("address").regex(searchTerm, "i")), Property.class);
  }

  void updateGeocode(Property property) {
    if(property.getId() == null || !StringUtils.equals(Optional.ofNullable(findById(property.getId())).map(Property::getAddress).orElse(null), property.getAddress())) {
      property.setLocation(geocodeService.getGeocode(property.getAddress()));
    }
  }
}
