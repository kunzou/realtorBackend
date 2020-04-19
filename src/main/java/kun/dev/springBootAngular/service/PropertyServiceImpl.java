package kun.dev.springBootAngular.service;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import kun.dev.springBootAngular.Domain.*;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
@Primary
public class PropertyServiceImpl implements PropertyService {
  private GeocodeService geocodeService;
  private MongoTemplate mongoTemplate;

  public PropertyServiceImpl(GeocodeService geocodeService, MongoTemplate mongoTemplate) {
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
    query.addCriteria(Criteria.where("propertyStatus").is(PropertyStatus.SALE.toString()));
    query.addCriteria(Criteria.where("hide").is(false));
    return mongoTemplate.find(query, Property.class);
  }

  @Override
  public Collection<Property> findSoldOrPurchased() {
    Query query = new Query();
    query.addCriteria(Criteria.where("propertyStatus").in(PropertyStatus.SOLD.toString(), PropertyStatus.PURCHASED.toString()));
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
//    Property persistedProperty = findById(property.getId()); //todo error handling

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

  @Override
  public Collection<PropertyCard> getPropertyCards() {
    return findSale().stream()
      .map(this::toPropertyCard)
      .collect(Collectors.toSet());
  }

  @Override
  public Collection<PropertyCard> getOpenHouseCards() {
    return findSale().stream()
      .filter(Property::hasOpenHouse)
      .map(this::toPropertyCard)
      .collect(Collectors.toSet());
  }

  @Override
  public Collection<PropertyCard> getHomePageCards() {
    Query query = new Query();
    query.addCriteria(Criteria.where("displayedOnHomePage").is(true));
    return mongoTemplate.find(query, Property.class).stream()
        .filter(property -> property.getPropertyStatus() != null)
        .sorted(Property.HOME_PAGE_LIST_COMPARATOR)
        .map(this::toPropertyCard)
        .collect(Collectors.toList());
  }

  @Override
  public Collection<Slide> getHomePageSlides() {
    Collection<Slide> slides;
    Collection<Property> properties = findSale();
    if(!properties.isEmpty()) {
      slides = properties.stream()
        .map(this::createSlides)
        .flatMap(Collection::stream)
        .collect(Collectors.toList());
    } else {
      slides = findSold().stream()
        .map(this::createSlideFromSold)
        .filter(Objects::nonNull)
        .collect(Collectors.toList());
    }

    return slides;
  }

  private Collection<Slide> createSlides(Property property) {
    return property.getAdditionalImages().stream()
      .map(image -> createSlide(property, image))
      .filter(Objects::nonNull)
      .collect(Collectors.toList());
  }

  private Slide createSlideFromSold(Property property) {
    return createSlide(property, property.getPrimaryImage());
  }

  private Collection<Property> findSold() {
    Query query = new Query();
    query.addCriteria(Criteria.where("propertyStatus").in(PropertyStatus.SOLD.toString()));
    query.addCriteria(Criteria.where("hide").is(false));
    return mongoTemplate.find(query, Property.class);
  }

  private Slide createSlide(Property property, Image image) {
    if(image == null) {
      return null;
    }
    Slide slide = new Slide();
    slide.setImage(image.getHugeLink()!=null?image.getHugeLink():image.getLink());
    slide.setOpenHouse(property.getOpenHouseDate());
    slide.setId(property.getId());
    slide.setPrice(property.getAskingPrice());
    slide.setText(property.getAddress());
    return slide;
  }

  private PropertyCard toPropertyCard(Property property) {
    PropertyCard propertyCard = new PropertyCard();
    propertyCard.setId(property.getId());
    propertyCard.setPrimaryImage(property.getPrimaryImage());
    propertyCard.setTag(property.getTag());
    propertyCard.setAddress(property.getAddress());
    propertyCard.setNeighborhood(property.getNeighborhood());
    propertyCard.setPropertyType(property.getPropertyType());
    propertyCard.setAskingPrice(property.getAskingPrice());
    propertyCard.setNumberOfRooms(property.getNumberOfRooms());
    propertyCard.setNumberOfWashrooms(property.getNumberOfWashrooms());
    propertyCard.setLocation(property.getLocation());
    propertyCard.setOpenHouseDate(property.getOpenHouseDate());
    propertyCard.setArea(property.getArea());
    propertyCard.setBrief(property.getBrief());
    if(property.getPropertyStatus().equals(PropertyStatus.SOLD.toString())) {
      propertyCard.setTag(property.getTag());
    }
    return propertyCard;
  }

  private void updateGeocode(Property property) {
    if(property.getId() == null || !StringUtils.equals(Optional.ofNullable(findById(property.getId())).map(Property::getAddress).orElse(null), property.getAddress())) {
      property.setLocation(geocodeService.getGeocode(property.getAddress()));
    }
  }

}
