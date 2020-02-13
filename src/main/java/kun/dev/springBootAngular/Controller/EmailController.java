package kun.dev.springBootAngular.Controller;

import kun.dev.springBootAngular.Domain.EmailDetail;
import kun.dev.springBootAngular.service.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

  private EmailService emailService;

  public EmailController(EmailService emailService) {
    this.emailService = emailService;
  }

  @PostMapping("/sendEmail")
  @CrossOrigin
  public ResponseEntity sendEmail(@RequestBody EmailDetail emailDetail) {
    boolean success = emailService.sendEmail(emailDetail);
    return success?ResponseEntity.ok().build():ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
  }

}
