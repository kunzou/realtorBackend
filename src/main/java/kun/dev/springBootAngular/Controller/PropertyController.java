package kun.dev.springBootAngular.Controller;

import kun.dev.springBootAngular.repository.PropertyRepository;
import kun.dev.springBootAngular.Domain.Property;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
public class PropertyController {
  private static final Logger logger = LoggerFactory.getLogger(PropertyController.class);

  private PropertyRepository repository;

  public PropertyController(PropertyRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/properties")
  @CrossOrigin(origins = "http://localhost:4200")
  public Collection<Property> getProperties() {
    return new ArrayList<>(repository.findAll());
  }

  @GetMapping("/properties/{id}")
  @CrossOrigin(origins = "http://localhost:4200")
  public Property getPropertyById(@PathVariable("id") String id) {
    return repository.findById(id)
        .orElse(null);
  }

  @GetMapping("/properties/")
  @CrossOrigin(origins = "http://localhost:4200")
  public Collection<Property> getPropertyByName(@RequestParam String name) {
    return repository.findAll().stream()
        .filter(property -> property.getName().contains(name.trim()))
        .collect(Collectors.toList());
  }

  @PostMapping("/properties")
  @CrossOrigin(origins = "http://localhost:4200")
  public Property addProperty(@RequestBody Property property) {
    return repository.save(property);
  }

  @PutMapping("/properties")
  @CrossOrigin(origins = "http://localhost:4200")
  public Property updateProperty(@RequestBody Property property) {
    return repository.save(property);
  }

  @DeleteMapping("/properties/{id}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity deleteProperty(@PathVariable("id") String id) {
    repository.deleteById(id);
    return ResponseEntity.ok().build();
  }
}
