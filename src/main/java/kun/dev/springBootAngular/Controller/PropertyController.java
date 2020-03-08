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
@RequestMapping("/api/properties")
public class PropertyController {
  private static final Logger logger = LoggerFactory.getLogger(PropertyController.class);

  @Autowired
  private PropertyService propertyService;

  public PropertyController(PropertyService propertyService) {
    this.propertyService = propertyService;
  }

  @GetMapping("")
  public Collection<Property> getProperties() {
    logger.info("getProperties");
    return propertyService.findAll();
  }

  @GetMapping("/soldPurchased")
  public Collection<Property> findSoldOrPurchased() {
    logger.info("findSoldOrPurchased");
    return propertyService.findSoldOrPurchased();
  }

  @GetMapping("/sale")
  public Collection<Property> findSale() {
    logger.info("findSale");
    return propertyService.findSale();
  }

  @GetMapping("/{id}")
  public Property getPropertyById(@PathVariable("id") String id) {
    logger.info("getPropertyById");
    return propertyService.findById(id);
  }

  @GetMapping("/search/")
  public Collection<Property> getPropertyByAddress(@RequestParam String address) {
    logger.info("getPropertyByAddress");
    return propertyService.findByAddress(address);
  }

  @PostMapping("")
  public Property addProperty(@RequestBody Property property) {
    logger.info("addProperty" + property);
    return propertyService.addNewProperty(property);
  }

  @PutMapping("")
  public Property updateProperty(@RequestBody Property property) {
    logger.info("updateProperty" + property);
    return propertyService.update(property);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity deleteProperty(@PathVariable("id") String id) {
    logger.info("deleteProperty" + id);
    propertyService.deleteById(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/openHouse")
  public Collection<PropertyCard> getPropertyCards() {
    logger.info("getPropertyCards");
    return propertyService.getOpenHouseCards();
  }

  @GetMapping("/homePage")
  public Collection<PropertyCard> getHomePageCards() {
    logger.info("getHomePageCards");
    return propertyService.getHomePageCards();
  }
}
