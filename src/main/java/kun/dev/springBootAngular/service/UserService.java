package kun.dev.springBootAngular.service;

import kun.dev.springBootAngular.Domain.User;
import kun.dev.springBootAngular.Domain.VisitHistory;
import org.apache.tomcat.jni.Local;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

      user = mongoTemplate.save(user);
    }
    mongoTemplate.save(new VisitHistory(ZonedDateTime.now().withZoneSameInstant(ZoneId.of("US/Central")).toLocalDateTime()));

    return user;
  }

  public User update(User user) {
    user = mongoTemplate.save(user);

    return user;
  }

  public List<VisitHistory> getVisitHistory() {
    List<VisitHistory> histories = mongoTemplate.findAll(VisitHistory.class);

    histories.stream()
      .filter(visit -> isOverTwoMonths(visit.getVisitDateTime()))
      .forEach(mongoTemplate::remove);

    List<VisitHistory> returnList = histories.stream()
      .filter(visit -> !isOverTwoMonths(visit.getVisitDateTime()))
      .collect(Collectors.toList());

    Collections.reverse(returnList);
    return returnList;
  }

  boolean isOverTwoMonths(LocalDateTime localDateTime) {
    return Period.between(localDateTime.toLocalDate(), LocalDateTime.now().toLocalDate()).getMonths() > 2;
  }

}
