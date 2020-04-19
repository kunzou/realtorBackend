package kun.dev.springBootAngular.Domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class VisitHistory {
  LocalDateTime visitDateTime;

  public VisitHistory() {
  }

  public VisitHistory(LocalDateTime visitDateTime) {
    this.visitDateTime = visitDateTime;
  }
}
