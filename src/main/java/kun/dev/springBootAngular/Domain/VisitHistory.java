package kun.dev.springBootAngular.Domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class VisitHistory {
  @Id
  @Indexed(unique = true)
  private String id;
  LocalDateTime visitDateTime;

  public VisitHistory() {
  }

  public VisitHistory(LocalDateTime visitDateTime) {
    this.visitDateTime = visitDateTime;
  }
}
