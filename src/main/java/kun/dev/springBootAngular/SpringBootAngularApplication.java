package kun.dev.springBootAngular;

import com.google.maps.model.LatLng;

import kun.dev.springBootAngular.Domain.Description;
import kun.dev.springBootAngular.Domain.Image;
import kun.dev.springBootAngular.Domain.Property;
import kun.dev.springBootAngular.Domain.User;
import kun.dev.springBootAngular.property.FileStorageProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.core.MongoTemplate;

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
      user.setBarcode(new Image("https://images.squarespace-cdn.com/content/v1/5d425c3accfbe50001c13b16/1577427337333-TDOXON0QY83MGV4TCOY2/ke17ZwdGBToddI8pDm48kP06O0_IHyRXSOOiqwgWaApZw-zPPgdn4jUwVcJE1ZvWEtT5uBSRWt4vQZAgTJucoTqqXjS3CfNDSuuf31e0tVEHLRkg2cosQUGLeQ33UzXdgIxPDaVwE3LlEpL74qP4JVW4jCyXLPvvdR287iymYt8/liudan+wechat.jpg"));

      user.setDisplayName(new Description("刘丹", "Dan Liu"));
      user.setEmail("liudan_109@hotmail.com");
      user.setPhoneNumber("204-228-4950");
      user.setAboutMe(new Description("从业近10年，师从温尼伯华人地产经纪鼻祖卜涧淞，现供职于卜涧淞团队，已帮助近500位客户找到了他们心中满意的家。\n" +
        "我目前正在考取评估师以及验房师资格证。 专注新建房以及开发。\n" +
        "我从客户角度出发，聆听客户需求，为您找出最佳解决方案，提供良好的售前，售中以及售后服务。", "How to translate your application\n" +
        "The JSON translation file\n" +
        "\n" +
        "cheat sheet\n" +
        "\n" +
        "Don't make these 7 mistakes we made when we started with ngx-translate!\n" +
        "\n" +
        "Learn more in our newsletter and get our free ngx-translate cheat-sheet: All you need to know on a single page.\n" +
        "\n" +
        "GET IT!\n" +
        "\n" +
        "Each language is stored in a separate .json file. Let's create the JSON file for the English translation: assets/i18n/en.json.\n" +
        "\n" +
        "ngx-translate can read 2 JSON formats:"));
      user.setAboutPageDescription(new Description("                                福居温尼伯\n" +
        "                          fujuwinnipeg.com\n" +
        "从业近10年，师从温尼伯华人地产经纪鼻祖卜涧淞，现供职于卜涧淞团队，已帮助近500位客户找到了他们心中满意的家。\n" +
        "我目前正在考取评估师以及验房师资格证。 专注新建房以及开发。\n" +
        "我从客户角度出发，聆听客户需求，为您找出最佳解决方案，提供良好的售前，售中以及售后服务。\n" +
        "\n" +
        "\n" +
        "联系方式\n" +
        "手机：204-228-4950\n" +
        "邮箱：liudan_109@hotmail.com\n" +
        "公司个人主页：https://www.dichanjingji.com/superdan\n" +
        "\n" +
        "\n" +
        "平方米地产\n" +
        "温尼伯第一个华人地产经纪公司\n" +
        "平方米地产- Square Meter Realty经纪公司 ，是一家提供温尼伯房地产销售，地产咨询，新经纪培训的地产经纪公司，由温尼伯资深房地产经纪人卜涧松创立。作为温尼伯唯一华人地产经纪公司，多数开心满意的客户是我们多年来收货的最宝贵财富。 我们追求的是团队精神，注重培训经纪的专业知识， 把专业，努力，负责任作为公司的服务宗旨，同时让更多的人理解我们的商业文化和规划。我们长期致力于招纳并培训出色的全方位地产经纪人。\n" +
        "我们的追求 关注我们您将得到温尼伯最新最全的买房，卖房，投资，房屋养护知识。我们的专业团队将为你提供专业的服务。\n" +
        "如有任何房屋买卖，房屋养护的问题都可以向我们咨询，我们会一一为你做出解答。我们期待着您的留言和来电！",
        "import translate service and add in constructor\n" +
          "\n" +
          "contructor(private translate: TranslateService){\n" +
          "}\n" +
          "I find it use this.translate.currentLang to get the current language\n" +
          "\n" +
          "shareedit\n" +
          "\n" +
          "edited Nov 9 '19 at 8:57\n" +
          "\n" +
          "\n" +
          "\n" +
          "Giovanni Dias\n" +
          "\n" +
          "881313 bronze badges\n" +
          "\n" +
          "answered Mar 9 '17 at 15:45\n" +
          "\n" +
          "\n" +
          "\n" +
          "Allen\n" +
          "\n" +
          "1,05711 gold badge77 silver badges1515 bronze badges\n" +
          "\n" +
          "how to access this translate.currentLang ? – godblessstrawberry Mar 26 '18 at 12:33\n" +
          "\n" +
          "6\n" +
          "\n" +
          "@godblessstrawberry just use this.translateService.currentLang, dont forget to add the TranslateService in construtor – Allen Mar 28 '18 at 9:18\n" +
          "\n" +
          "Is there a way to get an observable of the current language? – Mateja Petrovic Mar 14 '19 at 12:57\n" +
          "\n" +
          "1\n" +
          "\n" +
          "@MatejaPetrović en.. I think current language is not a observable like property, but you can subscribe the onLangChange of translate service. – Allen Mar 15 '19 at 6:40"));
      user.setVersion(1L);
      mongoTemplate.insert(user);
    }
  }

  private void addDefaultProperty() {
    if(mongoTemplate.findAll(Property.class).isEmpty()) {
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
      property.setPropertyType("propertyType.house");
      property.setPropertyStatus("Sale");
      property.setYearBuilt(2003);
      property.setDescription(new Description("所谓“转阴后复发”的问题，实际上是病毒核酸检测灵敏度的问题，需要病毒到达一定的量，核酸检测才会显示阳性。\n" +
          "\n" +
          "严格来说，“复发”应该是在临床症状完全消除之后。如果患者只是核酸检测转阴，但是还有发烧、呼吸窘迫等问题，并不能说明已经治愈，如果此时病情恶化，不能说是复发，只能说是“病情反复”。\n" +
          "\n" +
          "查证者：一节生姜 | 宾夕法尼亚大学医学院病理及实验医药系研究副教授\n" +
          "\n" +
          "1. 核酸检测存在假阴性的情况\n" +
          "\n" +
          "在普通人的印象中，只要有病毒感染，核酸检测就应该是阳性；如果检查是阴性，说明没有新型冠状病毒感染。\n" +
          "\n" +
          "但这是理想中的检查状况，现实不一定完全吻合。\n" +
          "\n" +
          "新型冠状病毒的核酸检测主要通过实时荧光RT-PCR来进行。首先要提取咽拭子中的RNA，然后经过酶促化学反应，扩增目标基因，才能检测出样品中是否有病毒基因。在这个过程中，如果一开始样品中的病毒拷贝太少，或者扩增时效率受到影响，都会影响检查结果。\n" +
          "\n" +
          "危重症医学专家、中国医学科学院院长王辰院士在2月5日接受央视采访时表示，新型冠状病毒感染的核酸检测 “只有30%～50%的阳性率”。也就是说，即便是确诊的病例中，也有不少是核酸检测没检测出来的，也就是所谓的假阴性。\n" +
          "\n" +
          "在试剂核酸检测操作中，一般是连续进行两次检查，只要有一次是阳性，就可以确诊；而需要两次结果都是阴性，才判定为阴性。通过这样的操作，可以把阳性率提高一些，把假阴性率降低。\n" +
          "\n" +
          "武汉病毒所之前对7个患者进行过核酸检测，第一次检测是2019年12月30日，6个咽拭子样本都是阳性的，但是到1月10日检测的时候，全都变为阴性了。但是，在1月13日的时候，这6个患者中还有5人有发烧症状，只有一人治愈出院 [1]。所以，在多数情况下，核酸检测变阴，可能说明病毒量减少，属于好转，并不等于病毒就没有了。\n" +
          "\n" +
          "所谓“转阴后复发”的问题，实际上是因为病毒量减少，核酸检测的灵敏度不够，检查结果显示阴性，但是随着病情进展，病毒量增加，再做核酸检测，结果就变阳性，感觉是“复发”了。\n" +
          "\n" +
          "但严格来说，“复发”应该是在临床症状完全消除之后。如果患者只是核酸检测转阴，但是还有发烧、呼吸窘迫等问题，并不能说明已经治愈。如果此时病情恶化，不能说是复发，只能说是“病情反复”。", "\"<p><img alt=\\\"\\\" src=\\\"https://static1.squarespace.com/static/5d425c3accfbe50001c13b16/5de5ec77483f966c5c8de16d/5de361735043fe7ceb3c5004/1577946685619/snapshot20191201004756.jpg?format=500w\\\" style=\\\"height:281px; width:500px\\\" /><br /> &nbsp;</p> <table border=\\\"0\\\" cellpadding=\\\"1\\\" cellspacing=\\\"1\\\" style=\\\"width:900px\\\"> <tbody> <tr> <td>Welcome to this gorgeous former gold award show home built in 2016. You will fall in love of this home once you step into it. It is impressed with a stunning living room with incredible big windows, built in speakers and double side fireplace. Modern open concept kitchen with high end appliances, quartz counter tops, luxury plumbing fixtures and backsplash. Dinning room beside kitchen and access to the large deck in the back yard. Three bedrooms and laundry room on second floor. Master bedroom with custom designed closet and a 5 piece ensuite with tub and glass shower. double sinks. Steel beams, High Efficiency Furnace and tons more! Walking distance to the new gym in bridgwater centre. Call to schedule your showing today!</td> </tr> </tbody> </table> <p>&nbsp;</p>\""));
      property.setNumberOfRooms(6);
      property.setNumberOfWashrooms(3);
      property.setArea(1850.0);
      property.setAskingPrice(BigDecimal.valueOf(440000));
      property.setHide(false);
      property.setLocation(new LatLng(49.8131752, -97.0837596));
      property.setVersion(1L);
      property.setTag(new Description("买我啊!", "Buy me!"));
      property.setYoutubeLink("https://www.youtube.com/watch?v=Xvm6LcWBzEM");
      property.setNeighborhood("River Park South");
      mongoTemplate.insert(property);
    }
  }
}
