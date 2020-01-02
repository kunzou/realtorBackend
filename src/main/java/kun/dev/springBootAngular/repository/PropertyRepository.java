package kun.dev.springBootAngular.repository;

import kun.dev.springBootAngular.Domain.Property;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PropertyRepository extends MongoRepository<Property, String> {

  @Query("{'propertyStatus': {$in: ['Sold', 'Purchased']}, 'hide':false}")
  public Collection<Property> findSoldOrPurchasedProperties();

  @Query("{'propertyStatus': 'Sale', 'hide':false}")
  public Collection<Property> findSaleProperties();

  @Query("{'address': { $regex: ?0, $options: 'i' }}")
  public Collection<Property> findByAddress(String address);
}
