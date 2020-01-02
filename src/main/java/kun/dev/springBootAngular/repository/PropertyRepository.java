package kun.dev.springBootAngular.repository;

import kun.dev.springBootAngular.Domain.Property;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collection;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface PropertyRepository extends MongoRepository<Property, String> {

  @Query("{'propertyStatus': {$in: ['Sold', 'Purchased']}, 'hide':false}")
  public Collection<Property> findSoldOrPurchasedProperties();

  @Query("{'propertyStatus': 'Sale', 'hide':false}")
  public Collection<Property> findSaleProperties();

  @Query("{'address': { $regex: ?0, $options: 'i' }}")
  public Collection<Property> findByAddress(String address);
}
