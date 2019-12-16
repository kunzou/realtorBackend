package kun.dev.springBootAngular;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Hero {
    @Id
    @GeneratedValue
    private Long id;
    private @NonNull String name;

    public Hero() {
    }

    public Hero(@NonNull String name) {
        this.name = name;
    }
}
