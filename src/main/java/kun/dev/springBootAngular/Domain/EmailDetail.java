package kun.dev.springBootAngular.Domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class EmailDetail {
  private String address;
  private String from;
  private String email;
  private String phoneNumber;
  private String message;
}






