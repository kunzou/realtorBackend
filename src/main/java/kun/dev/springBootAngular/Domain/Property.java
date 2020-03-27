package kun.dev.springBootAngular.Domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

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
  private Description brief;
  private Description description;
  private Integer numberOfRooms;
  private Number numberOfWashrooms;
  private Number area;
  private BigDecimal askingPrice;
  private BigDecimal finalPrice;
  private LocalDateTime onMarketSince;
  private LocalDateTime dealDate;
  private boolean hide;
  private boolean displayedOnHomePage;
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
  private Collection<OtherFeature> OtherRemaining;
  private Collection<Highlight> features;
  private Collection<OtherFeature> otherFeatures;
  private LocalDateTime openHouseDate;

  public static final Comparator<Property> HOME_PAGE_LIST_COMPARATOR = Comparator.comparing(Property::getPropertyStatus).thenComparing(Property::getOnMarketSince, Comparator.nullsLast(Comparator.reverseOrder()));

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

  public Collection<OtherFeature> getOtherFeatures() {
    if(otherFeatures == null) {
      otherFeatures = Collections.emptySet();
    }
    return otherFeatures;
  }

  public Collection<OtherFeature> getOtherRemaining() {
    if(OtherRemaining == null) {
      OtherRemaining = Collections.emptySet();
    }
    return OtherRemaining;
  }

  public Collection<String> getRemaining() {
    if(remaining == null) {
      remaining = Collections.emptySet();
    }
    return remaining;
  }

  public Description getBrief() {
    return brief == null? new Description():brief;
  }

  public Description getDescription() {
    return description == null? new Description():description;
  }

  public Description getTag() {
    return tag == null? new Description():tag;
  }

  public boolean hasOpenHouse() {
    return PropertyStatus.SALE.toString().equals(propertyStatus) && openHouseDate != null && !openHouseDate.toLocalDate().isBefore(LocalDate.now());
  }
}

