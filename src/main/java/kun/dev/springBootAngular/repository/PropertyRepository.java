package kun.dev.springBootAngular.repository;

import kun.dev.springBootAngular.Domain.Property;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface PropertyRepository extends MongoRepository<Property, String> {
//  public Property findByName(String name);
}
