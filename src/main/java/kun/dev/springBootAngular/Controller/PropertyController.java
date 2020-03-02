package kun.dev.springBootAngular.Controller;

import kun.dev.springBootAngular.Domain.Property;
import kun.dev.springBootAngular.Domain.PropertyCard;
import kun.dev.springBootAngular.service.PropertyService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@CrossOrigin
public class PropertyController {
  private static final Logger logger = LoggerFactory.getLogger(PropertyController.class);

  @Autowired
  private PropertyService propertyService;

  public PropertyController(PropertyService propertyService) {
    this.propertyService = propertyService;
  }

  @GetMapping("/properties")
  public Collection<Property> getProperties() {
    logger.info("getProperties");
    return propertyService.findAll();
  }

  @GetMapping("/properties/soldPurchased")
  public Collection<Property> findSoldOrPurchased() {
    logger.info("findSoldOrPurchased");
    return propertyService.findSoldOrPurchased();
  }

  @GetMapping("/properties/sale")
  public Collection<Property> findSale() {
    logger.info("findSale");
    return propertyService.findSale();
  }

  @GetMapping("/properties/{id}")
  public Property getPropertyById(@PathVariable("id") String id) {
    logger.info("getPropertyById");
    return propertyService.findById(id);
  }

  @GetMapping("/properties/")
  public Collection<Property> getPropertyByAddress(@RequestParam String address) {
    logger.info("getPropertyByAddress");
    return propertyService.findByAddress(address);
  }

  @PostMapping("/properties")
  public Property addProperty(@RequestBody Property property) {
    logger.info("addProperty" + property);
    return propertyService.addNewProperty(property);
  }

  @PutMapping("/properties")
  public Property updateProperty(@RequestBody Property property) {
    logger.info("updateProperty" + property);
    return propertyService.update(property);
  }

  @DeleteMapping("/properties/{id}")
  public ResponseEntity deleteProperty(@PathVariable("id") String id) {
    logger.info("deleteProperty" + id);
    propertyService.deleteById(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/properties/openHouse")
  public Collection<PropertyCard> getPropertyCards() {
    logger.info("getPropertyCards");
    return propertyService.getOpenHouseCards();
  }

  @GetMapping("/properties/homePage")
  public Collection<PropertyCard> getHomePageCards() {
    logger.info("getHomePageCards");
    return propertyService.getHomePageCards();
  }
}
