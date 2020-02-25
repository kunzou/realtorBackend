package kun.dev.springBootAngular.Domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.index.Indexed;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class EmailSubscription {
  @Id
  @Indexed(unique = true)
	private String id;
  private String email;
  private String languagePreference;
  private boolean property;
  private boolean blog;

	public EmailSubscription() {
	}
}
