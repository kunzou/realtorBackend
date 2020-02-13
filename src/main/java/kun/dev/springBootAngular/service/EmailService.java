package kun.dev.springBootAngular.service;

import kun.dev.springBootAngular.Domain.EmailDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService {
  private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

  public boolean sendEmail(EmailDetail emailDetail) {
    boolean success = true;

    try {
      Transport.send(createMessage(emailDetail));
      logger.info("Email sent: "+emailDetail.toString());

    } catch (Exception e) {
      success = false;
      logger.error("Error: ", e);
    }

    return success;
  }

  private Message createMessage(EmailDetail emailDetail) throws Exception {
    final String username = System.getenv("EMAIL_USERNAME");
    final String password = System.getenv("EMAIL_PASSWORD");

    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.live.com");
    props.put("mail.smtp.debug", "true");
    props.put("mail.smtp.port", "587");

    Session session = Session.getInstance(props,
      new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
          return new PasswordAuthentication(username, password);
        }
      });
    session.setDebug(true);
    Message message = new MimeMessage(session);
    message.setFrom(new
      InternetAddress(System.getenv("EMAIL_USERNAME")));
    message.setRecipients(Message.RecipientType.TO,
      InternetAddress.parse(System.getenv("EMAIL_RECIPIENT")));
    message.setSubject("咨询"+emailDetail.getAddress());
    message.setText(
      "咨询人:"+emailDetail.getFrom()+"\n"+
        "邮箱:"+emailDetail.getEmail()+"\n"+
        "电话:"+emailDetail.getPhoneNumber()+"\n"+
        "信息:"+emailDetail.getMessage()
    );

    return message;
  }
}
