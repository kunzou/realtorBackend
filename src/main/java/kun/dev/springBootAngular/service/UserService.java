package kun.dev.springBootAngular.service;

import kun.dev.springBootAngular.Domain.User;
import kun.dev.springBootAngular.Domain.VisitHistory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.Collection;

@Service
public class UserService {
  private MongoTemplate mongoTemplate;

  public UserService(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  public Collection<User> findAll() {
    return mongoTemplate.findAll(User.class);
  }

  public User findById(String id) {
    return mongoTemplate.findOne(Query.query(Criteria.where("id").is(id)), User.class);
  }

  public User getOwner() {
    User user = mongoTemplate.findOne(Query.query(Criteria.where("owner").is(true)), User.class);
    if(user != null) {
      user.setTotalVisits(user.getTotalVisits() + 1);
      user.addVisitHistory(new VisitHistory(ZonedDateTime.now().withZoneSameInstant(ZoneId.of("US/Central")).toLocalDateTime()));
      if(user.getVisitHistories().size() > 500) {
        user.getVisitHistories().remove(500);
      }
      user = mongoTemplate.save(user);
    }

    return user;
  }

  public User update(User user) {
    user = mongoTemplate.save(user);

    return user;
  }

}
