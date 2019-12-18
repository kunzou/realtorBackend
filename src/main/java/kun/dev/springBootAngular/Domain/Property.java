package kun.dev.springBootAngular.Domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.awt.*;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Property {
    @Id
    private String id;
    private @NonNull String name;
    private String address;
    private String imgUrl;

    public Property() {
    }

    public Property(@NonNull String name) {
        this.name = name;
    }
}
