package kun.dev.springBootAngular;

import com.google.maps.model.LatLng;
import kun.dev.springBootAngular.Domain.Image;
import kun.dev.springBootAngular.Domain.Property;
import kun.dev.springBootAngular.property.FileStorageProperties;
import kun.dev.springBootAngular.repository.PropertyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.math.BigDecimal;
import java.util.Arrays;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class SpringBootAngularApplication implements CommandLineRunner {
  private PropertyRepository repository;

  public SpringBootAngularApplication(PropertyRepository repository) {
    this.repository = repository;
  }

  public static void main(String[] args) {
	  SpringApplication.run(SpringBootAngularApplication.class, args);
	}

  @Override
  public void run(String... args) throws Exception {
    if(!repository.findById("5e0a745327a44b424ec39797").isPresent()) {
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
      repository.insert(property);
    }
  }
}
