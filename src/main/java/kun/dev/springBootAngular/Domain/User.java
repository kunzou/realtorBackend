package kun.dev.springBootAngular.Domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Collection;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User {
  @Id
  private String id;
  private String firstName;
  private String lastName;
  private String displayName;
  private String email;
  private String homePage;
  private Image portrait;
  private Image barcode;
  private Image signature;
  private String phoneNumber;
  private String aboutMe;
  private String aboutCompany;
  private String aboutPageDescription;
  private boolean owner;
  private Long version;
}

















