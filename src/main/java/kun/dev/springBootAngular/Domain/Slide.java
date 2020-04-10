package kun.dev.springBootAngular.Domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Slide {
  private String image;
  private String text;
  private String id;
  private BigDecimal price;
  private LocalDateTime openHouse;
}
