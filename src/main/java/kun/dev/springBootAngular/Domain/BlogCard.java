package kun.dev.springBootAngular.Domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class BlogCard {
  private String id;
  private String category;
  private Description brief;
  private Description title;
  private LocalDateTime date;
  private Image coverImage;
  private int viewCount;

  public Image getCoverImage() {
    if(coverImage == null) {
      coverImage = new Image();
    }
    return coverImage;
  }

}
