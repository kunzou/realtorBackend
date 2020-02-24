package kun.dev.springBootAngular.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.mongodb.client.result.DeleteResult;

import kun.dev.springBootAngular.Domain.EmailSubscription;
import kun.dev.springBootAngular.Domain.Property;

@Service
public class NewsLetterService {
  private static final Logger logger = LoggerFactory.getLogger(NewsLetterService.class);
  private MongoTemplate mongoTemplate;

	public NewsLetterService(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public void addToNewsLetterSubscription(EmailSubscription emailSubscription) {
		mongoTemplate.save(emailSubscription);
	}

	public DeleteResult unsubscrible(String email) {
		return mongoTemplate.remove(new Query(Criteria.where("email").is(email)), EmailSubscription.class);
	}
}
