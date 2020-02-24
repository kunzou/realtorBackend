package kun.dev.springBootAngular.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.client.result.DeleteResult;

import kun.dev.springBootAngular.Domain.EmailSubscription;
import kun.dev.springBootAngular.service.NewsLetterService;

@RestController
@CrossOrigin
@RequestMapping("/subscription")
public class NewsLetterController {
	private static final Logger logger = LoggerFactory.getLogger(NewsLetterController.class);
	private NewsLetterService newsLetterService;

	public NewsLetterController(NewsLetterService newsLetterService) {
		this.newsLetterService = newsLetterService;
	}

	@PostMapping("/subscribe")
  public ResponseEntity addProperty(@RequestBody EmailSubscription emailSubscription) {
    newsLetterService.addToNewsLetterSubscription(emailSubscription);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/unsubscribe/{email}")
  public ResponseEntity deleteProperty(@PathVariable("email") String email) {
    logger.info("unsubscribe: " + email);
    DeleteResult deleteResult = newsLetterService.unsubscrible(email);
    if(deleteResult.wasAcknowledged()) {
    	return ResponseEntity.ok().build();
		} else {
			return new ResponseEntity("Unsubscrible not successful. Please contact ... for unsubscription", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
  }
}
