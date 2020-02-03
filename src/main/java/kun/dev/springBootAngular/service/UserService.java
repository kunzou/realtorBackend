package kun.dev.springBootAngular.service;

import kun.dev.springBootAngular.Domain.User;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

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
    return mongoTemplate.findOne(Query.query(Criteria.where("owner").is(true)), User.class);
  }

  public User update(User user) {
//    User saveduser = findById(user.getId());
//    user.setVersion(user.getVersion()+1);
    user = mongoTemplate.save(user);

    return user;
  }

}
