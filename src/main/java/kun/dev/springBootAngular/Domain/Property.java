package kun.dev.springBootAngular.Domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

import com.google.maps.model.LatLng;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Property {
  @Id
  private String id;
  private @NonNull String address;
  private Image primaryImage;
  private Collection<Image> additionalImages;
  private String propertyType;
  private String propertyStatus;
  private Integer yearBuilt;
  private String description;
  private Integer numberOfRooms;
  private Number numberOfWashrooms;
  private Double area;
  private BigDecimal askingPrice;
  private BigDecimal finalPrice;
  private LocalDate onMarketSince;
  private LocalDate dealDate;
  private boolean hide;
  private LatLng location;
  private Long version;
  private String aboutPageComment;

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
}
