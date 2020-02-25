package kun.dev.springBootAngular.service;

import kun.dev.springBootAngular.Domain.EmailDetail;
import kun.dev.springBootAngular.Domain.EmailSubscription;
import kun.dev.springBootAngular.Domain.Emailable;
import kun.dev.springBootAngular.Domain.Property;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmailService {
  private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
  private static String PROPERTY_TEMPLATE_SUBJECT = "";
  private static String PROPERTY_TEMPLATE_TEXT =
"<!DOCTYPE html>\n" +
    "<html>\n" +
    "<head>\n" +
    "\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
    "\t  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css\">\n" +
    "\t  <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">\n" +
    "\t  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js\"></script>\n" +
    "\n" +
    "\t<style>\n" +
    "\t\t.card {\n" +
    "\t\t\tbox-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);\n" +
    "\t\t\ttransition: 0.3s;\n" +
    "\t\t\twidth: 40%;\n" +
    "\t\t}\n" +
    "\n" +
    "\t\t.card:hover {\n" +
    "\t\t\tbox-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);\n" +
    "\t\t}\n" +
    "\n" +
    "\t\t.container {\n" +
    "\t\t\tpadding: 2px 16px;\n" +
    "\t\t}\n" +
    "\t</style>\n" +
    "</head>\n" +
    "<body>\n" +
    "\t<div class=\"card\">\n" +
    "\t\t<a href=\"http://localhost:4200/property/%s\" target=\"_blank\" class=\"\">\n" +
    "\t\t\t<img src=\"%s\" alt=\"Avatar\" style=\"width:100%\">\n" +
    "\t\t</a>\n" +
    "\t\t<div class=\"container pt-3\">\n" +
    "\t\t\t<h3><a href=\"http://localhost:4200/property/%s\" target=\"_blank\" class=\"\">%s</a></h3>\n" +
    "\t\t\t<h4><a href=\"http://localhost:4200/property/%s\" target=\"_blank\" class=\"\">$%s</a></h4>\n" +
    "\t\t\t<div class=\"container\">\n" +
    "\t\t\t  <div class=\"row\">\n" +
    "\t\t\t    <div class=\"col-sm-4\">\n" +
    "\t\t\t      <h5><i class=\"fa fa-cube\"></i> %s</h5>\n" +
    "\t\t\t    </div>\n" +
    "\t\t\t    <div class=\"col-sm-4\">\n" +
    "\t\t\t      <h5><i class=\"fa fa-bed\"></i> %s</h5>\n" +
    "\t\t\t    </div>\n" +
    "\t\t\t    <div class=\"col-sm-4\">\n" +
    "\t\t\t      <h5><i class=\"fa fa-bath\"></i> %s</h5>        \n" +
    "\t\t\t    </div>\n" +
    "\t\t\t  </div>\n" +
    "\t\t\t</div>\t\n" +
    "\t\t</div>\n" +
    "\t</div>\n" +
    "</body>\n" +
    "</html> \n";

  public void sendEmail(EmailDetail emailDetail) throws MessagingException {
    Transport.send(createMessage(emailDetail));
    logger.info("Email sent: "+emailDetail.toString());
  }

  private Message createMessage(EmailDetail emailDetail) throws MessagingException {
    Message message = createEmailableMessage(System.getenv("EMAIL_RECIPIENT"));

    message.setSubject("咨询"+emailDetail.getAddress());
    message.setText(
      "咨询人:"+emailDetail.getFrom()+"\n"+
        "邮箱:"+emailDetail.getEmail()+"\n"+
        "电话:"+emailDetail.getPhoneNumber()+"\n"+
        "信息:"+emailDetail.getMessage()
    );

    return message;
  }

  private void pushPropertyNewsLetter(String id, Set<EmailSubscription> recipients, Property property) throws MessagingException {

    Transport.send(createPropertiesPushMessage(id, recipients, property, "zh"));
    Transport.send(createPropertiesPushMessage(id, recipients, property, "en"));
  }

  private Message createPropertiesPushMessage(String id, Set<EmailSubscription> recipients, Property property, String language) throws MessagingException {
    Set<String> emails = recipients.stream()
        .filter(emailSubscription -> language.equals(emailSubscription.getLanguagePreference()))
        .map(EmailSubscription::getEmail)
        .collect(Collectors.toSet());
    Message message = createEmailableMessage(emails);
    message.setSubject("新房上市 - 福居温尼伯"); //zh/en
    message.setText(String.format(PROPERTY_TEMPLATE_TEXT,
        "5e0a745327a44b424ec39797",
        "https://i.imgur.com/PZjeqWx.jpg",
        "5e0a745327a44b424ec39797",
        "83 Wilford Close",
        "5e0a745327a44b424ec39797",
        "520,000",
        "1850",
        "6",
        "4")
    );

    return message;

//    message.setText(String.format(PROPERTY_TEMPLATE_TEXT,
//        property.getId(),
//        property.getPrimaryImage().getMediumLink(),
//        property.getId(),
//        property.getAddress(),
//        property.getId(),
//        property.getAskingPrice(),
//        property.getArea(),
//        property.getNumberOfRooms(),
//        property.getNumberOfWashrooms())
//    );
  }

  private Message createEmailableMessage(String to) throws MessagingException  {
    return createEmailableMessage(to, null);
  }

  private Message createEmailableMessage(Set<String> bcc) throws MessagingException  {
    return createEmailableMessage(null, bcc);
  }

  private Message createEmailableMessage(String to, Set<String> bcc) throws MessagingException  {
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
    session.setDebug(false);
    Message message = new MimeMessage(session);
    message.setFrom(new InternetAddress(System.getenv("EMAIL_USERNAME")));

    if(StringUtils.isNotBlank(to)) {
      message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
    }

    if(CollectionUtils.isEmpty(bcc)) {
      message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(String.join(", ", bcc)));
    }

    return message;
  }
}
