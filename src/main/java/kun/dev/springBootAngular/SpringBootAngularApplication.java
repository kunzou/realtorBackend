package kun.dev.springBootAngular;

import com.google.maps.model.LatLng;
import kun.dev.springBootAngular.Domain.Image;
import kun.dev.springBootAngular.Domain.Property;
import kun.dev.springBootAngular.Domain.User;
import kun.dev.springBootAngular.property.FileStorageProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.util.Arrays;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class SpringBootAngularApplication implements CommandLineRunner {
  private MongoTemplate mongoTemplate;

  public SpringBootAngularApplication(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  public static void main(String[] args) {
	  SpringApplication.run(SpringBootAngularApplication.class, args);
	}

  @Override
  public void run(String... args) throws Exception {
    addDefaultProperty();
    addDefaultUser();
  }

  private void addDefaultUser() {
    if(mongoTemplate.findAll(User.class).isEmpty()) {
      User user = new User();
      user.setOwner(true);
      user.setPortrait(new Image("https://images.squarespace-cdn.com/content/v1/5d425c3accfbe50001c13b16/1565318289989-NHKHVZCS70SENY9W42RM/ke17ZwdGBToddI8pDm48kMZDWQmjgHJUcPr17npBZmRZw-zPPgdn4jUwVcJE1ZvWEtT5uBSRWt4vQZAgTJucoTqqXjS3CfNDSuuf31e0tVGgxaxAvrH_Qq7-L1cbOGwu4e5jFLqLKU4CoUjr8FaVAh926scO3xePJoa6uVJa9B4/guant_20171111_D811996-removebg-preview.png"));
      user.setSignature(new Image("https://images.squarespace-cdn.com/content/v1/5d425c3accfbe50001c13b16/1577427785882-KLNNU68VIVEGNS6CK7ZX/ke17ZwdGBToddI8pDm48kLh1saP75vC4rWxvy1v4RDRZw-zPPgdn4jUwVcJE1ZvWQUxwkmyExglNqGp0IvTJZUJFbgE-7XRK3dMEBRBhUpzBZc9cn_KgttuoIZDpUtz9bBDeWYRuJFJdEJJCJYPN2DsVba-UqOHZg-79dIrT3ok/4818e8d2768148feb11621d3812cf047.PNG"));
      user.setWeChatBarcode(new Image("https://images.squarespace-cdn.com/content/v1/5d425c3accfbe50001c13b16/1577427337333-TDOXON0QY83MGV4TCOY2/ke17ZwdGBToddI8pDm48kP06O0_IHyRXSOOiqwgWaApZw-zPPgdn4jUwVcJE1ZvWEtT5uBSRWt4vQZAgTJucoTqqXjS3CfNDSuuf31e0tVEHLRkg2cosQUGLeQ33UzXdgIxPDaVwE3LlEpL74qP4JVW4jCyXLPvvdR287iymYt8/liudan+wechat.jpg"));
      user.setDisplayName("刘丹");
      user.setEmail("liudan_109@hotmail.com");
      user.setPhoneNumber("204-228-4950");
      user.setAboutMe("从业近10年，师从温尼伯华人地产经纪鼻祖卜涧淞，现供职于卜涧淞团队，已帮助近500位客户找到了他们心中满意的家。\n" +
        "我目前正在考取评估师以及验房师资格证。 专注新建房以及开发。\n" +
        "我从客户角度出发，聆听客户需求，为您找出最佳解决方案，提供良好的售前，售中以及售后服务。");
      user.setAboutCompany("平方米地产\n" +
        "温尼伯第一个华人地产经纪公司\n" +
        "平方米地产- Square Meter Realty经纪公司 ，是一家提供温尼伯房地产销售，地产咨询，新经纪培训的地产经纪公司，由温尼伯资深房地产经纪人卜涧松创立。作为温尼伯唯一华人地产经纪公司，多数开心满意的客户是我们多年来收货的最宝贵财富。 我们追求的是团队精神，注重培训经纪的专业知识， 把专业，努力，负责任作为公司的服务宗旨，同时让更多的人理解我们的商业文化和规划。我们长期致力于招纳并培训出色的全方位地产经纪人。\n" +
        "我们的追求 关注我们您将得到温尼伯最新最全的买房，卖房，投资，房屋养护知识。我们的专业团队将为你提供专业的服务。\n" +
        "如有任何房屋买卖，房屋养护的问题都可以向我们咨询，我们会一一为你做出解答。我们期待着您的留言和来电！");
      user.setVersion(1L);
      mongoTemplate.insert(user);
    }
  }

  private void addDefaultProperty() {
    if(mongoTemplate.findOne(Query.query(Criteria.where("id").is("5e0a745327a44b424ec39797")), Property.class) == null) {
      Property property = new Property();
      property.setId("5e0a745327a44b424ec39797");
      property.setAddress("83 Wilford Close");
      Image primaryImage = new Image();
      primaryImage.setLink("https://i.imgur.com/PZjeqWx.jpg");
      primaryImage.setSmallLink("https://i.imgur.com/PZjeqWxt.jpg");
      primaryImage.setMediumLink("https://i.imgur.com/PZjeqWxm.jpg");
      primaryImage.setLargeLink("https://i.imgur.com/PZjeqWxl.jpg");
      property.setPrimaryImage(primaryImage);
      Image image1 = new Image();image1.setLink("https://i.imgur.com/sPjbNp8.jpg");
      Image image2 = new Image();image2.setLink("https://i.imgur.com/c4MIwvq.jpg");
      Image image3 = new Image();image3.setLink("https://i.imgur.com/ENfQHHL.jpg");
      Image image4 = new Image();image4.setLink("https://i.imgur.com/40YGzUc.jpg");
      Image image5 = new Image();image5.setLink("https://i.imgur.com/YNzAxcy.jpg");
      Image image6 = new Image();image6.setLink("https://i.imgur.com/ovGR5kR.jpg");
      Image image7 = new Image();image7.setLink("https://i.imgur.com/3Hlr3OF.jpg");
      Image image8 = new Image();image8.setLink("https://i.imgur.com/MCxCuZ0.jpg");
      Image image9 = new Image();image9.setLink("https://i.imgur.com/bL30iEU.jpg");
      Image imagea = new Image();imagea.setLink("https://i.imgur.com/3Opedhf.jpg");
      Image imageb = new Image();imageb.setLink("https://i.imgur.com/GVhRsZJ.jpg");
      Image imagec = new Image();imagec.setLink("https://i.imgur.com/6l7wVIh.jpg");
      Image imaged = new Image();imaged.setLink("https://i.imgur.com/Xkpy8HA.jpg");
      Image imagee = new Image();imagee.setLink("https://i.imgur.com/fPv2nTc.jpg");
      Image imagef = new Image();imagef.setLink("https://i.imgur.com/4fGSsAi.jpg");
      Image imageg = new Image();imageg.setLink("https://i.imgur.com/1ta6axv.jpg");
      Image imageh = new Image();imageh.setLink("https://i.imgur.com/MH2t3dx.jpg");
      Image imagei = new Image();imagei.setLink("https://i.imgur.com/IkuZCbf.jpg");
      Image imagej = new Image();imagej.setLink("https://i.imgur.com/27oG1To.jpg");
      Image imagek = new Image();imagek.setLink("https://i.imgur.com/v5SCv0r.jpg");
      property.setAdditionalImages(Arrays.asList(image1,image2,image3,image4,image5,image6,image7,image8,image9,imagea,imageb,imagec,imaged,imagee,imagef,imageg,imageh,imagei,imagej,imagek));
      property.setPropertyType("House");
      property.setPropertyStatus("Sale");
      property.setYearBuilt(2003);
      property.setDescription("Welcome to this gorgeous former gold award show home built in 2016. You will fall in love of this home once you step into it. It is impressed with a stunning living room with incredible big windows, built in speakers and double side fireplace. Modern open concept kitchen with high end appliances, quartz counter tops, luxury plumbing fixtures and backsplash. Dinning room beside kitchen and access to the large deck in the back yard. Three bedrooms and laundry room on second floor. Master bedroom with custom designed closet and a 5 piece ensuite with tub and glass shower. double sinks. Steel beams, High Efficiency Furnace and tons more! Walking distance to the new gym in bridgwater centre. Call to schedule your showing today!");
      property.setNumberOfRooms(6);
      property.setNumberOfWashrooms(3);
      property.setArea(1850.0);
      property.setAskingPrice(BigDecimal.valueOf(440000));
      property.setHide(false);
      property.setLocation(new LatLng(49.8131752, -97.0837596));
      property.setVersion(1L);
      property.setTag("买我啊");
      property.setYoutubeLink("https://www.youtube.com/watch?v=Xvm6LcWBzEM");
      property.setNeighborhood("River Park South");
      mongoTemplate.insert(property);
    }
  }
}
