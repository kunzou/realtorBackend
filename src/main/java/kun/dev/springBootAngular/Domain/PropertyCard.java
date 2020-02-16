package kun.dev.springBootAngular.Domain;

import com.google.maps.model.LatLng;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PropertyCard {
  private String id;
  private Image primaryImage;
  private Description tag;
  private String address;
  private String neighborhood;
  private String propertyType;
  private Number askingPrice;
  private Number numberOfRooms;
  private Number numberOfWashrooms;
  private Number area;
  private LocalDateTime openHouseDate;
  private LatLng location;
}












