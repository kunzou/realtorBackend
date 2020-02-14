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
  private Description displayName;
  private String email;
  private String homePage;
  private Image portrait;
  private Image barcode;
  private Image signature;
  private String phoneNumber;
  private String officePhoneNumber;
  private String faxNumber;
  private Description aboutMe;
  private Description aboutCompany;
  private Description aboutPageDescription;
  private boolean owner;
  private Long version;
  private String facebook;
  private String instagram;
  private String youtube;
}



















