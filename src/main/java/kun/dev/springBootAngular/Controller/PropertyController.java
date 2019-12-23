package kun.dev.springBootAngular.Controller;

import kun.dev.springBootAngular.Domain.Property;
import kun.dev.springBootAngular.service.PropertyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class PropertyController {
  private static final Logger logger = LoggerFactory.getLogger(PropertyController.class);

  private PropertyService propertyService;

  public PropertyController(PropertyService propertyService) {
    this.propertyService = propertyService;
  }

  @GetMapping("/properties")
  @CrossOrigin(origins = "http://localhost:4200")
  public Collection<Property> getProperties() {
    logger.info("getProperties");
    return propertyService.findAll();
  }

  @GetMapping("/properties/{id}")
  @CrossOrigin(origins = "http://localhost:4200")
  public Property getPropertyById(@PathVariable("id") String id) {
    logger.info("getPropertyById");
    return propertyService.findById(id)
        .orElse(null);
  }

  @GetMapping("/properties/")
  @CrossOrigin(origins = "http://localhost:4200")
  public Collection<Property> getPropertyByAddress(@RequestParam String address) {
    logger.info("getPropertyByAddress");
    return propertyService.findByAddress(address);
  }

  @PostMapping("/properties")
  @CrossOrigin(origins = "http://localhost:4200")
  public Property addProperty(@RequestBody Property property) {
    logger.info("addProperty" + property);
    return propertyService.addNewProperty(property);
  }

  @PutMapping("/properties")
  @CrossOrigin(origins = "http://localhost:4200")
  public Property updateProperty(@RequestBody Property property) {
    logger.info("updateProperty" + property);
    return propertyService.update(property);
  }

  @DeleteMapping("/properties/{id}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity deleteProperty(@PathVariable("id") String id) {
    logger.info("deleteProperty" + id);
    propertyService.deleteById(id);
    return ResponseEntity.ok().build();
  }
}
