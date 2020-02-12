package kun.dev.springBootAngular.Domain;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collection;
import java.util.Collections;

import com.google.maps.model.LatLng;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.index.Indexed;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Property {
  @Id
  @Indexed(unique = true)
  private String id;
  private @NonNull String address;
  private Image primaryImage;
  private Collection<Image> additionalImages;
  private String source;
  private String propertyType;
  private String propertyStatus;
  private Integer yearBuilt;
  private Description description;
  private Integer numberOfRooms;
  private Number numberOfWashrooms;
  private Double area;
  private BigDecimal askingPrice;
  private BigDecimal finalPrice;
  private Instant onMarketSince;
  private Instant dealDate;
  private boolean hide;
  private LatLng location;
  private Long version;
  private String aboutPageComment;
  private String youtubeLink;
  private Description tag;
  private String neighborhood;
  private Integer levels;
  private Integer listingNumber;
  private BigDecimal propertyTax;
  private String garage;
  private String basementCondition;
  private Collection<String> upgrades;
  private Integer lotArea;
  private String propertyStyle;
  private String garageSize;
  private String usage;
  private String holdType;
  private Collection<String> remaining;
  private Collection<Highlight> features;

  public Property() {
  }

  public Property(@NonNull String address) {
    this.address = address;
  }

  public Collection<Image> getAdditionalImages() {
    if(additionalImages == null) {
      additionalImages = Collections.emptySet();
    }
    return additionalImages;
  }

  public Collection<Highlight> getFeatures() {
    if(features == null) {
      features = Collections.emptySet();
    }
    return features;
  }

  public Collection<String> getRemaining() {
    if(remaining == null) {
      remaining = Collections.emptySet();
    }
    return remaining;
  }
}
