package kun.dev.springBootAngular.Controller;

import javax.mail.MessagingException;

import kun.dev.springBootAngular.Domain.Description;
import kun.dev.springBootAngular.Domain.EmailDetail;
import kun.dev.springBootAngular.service.EmailService;

import org.springframework.http.HttpHeaders;
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

  @PostMapping("/api/sendEmail")
  public ResponseEntity sendEmail(@RequestBody EmailDetail emailDetail) {
    try {
      emailService.sendEmail(emailDetail);
      return new ResponseEntity(new Description("消息发送成功，聃哥会尽快回复您", "Message sent successfully. Dan will answer you as soon as possible"),
          new HttpHeaders(), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity(new Description("对不起消息发送失败。请直接电话，短信或微信联系聃哥", "Sorry email service failed. Please call, text or email Dan for assistance"),
          new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
