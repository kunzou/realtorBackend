package kun.dev.springBootAngular.Domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.index.Indexed;
import sun.security.krb5.internal.crypto.Des;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Blog {
  @Id
  @Indexed(unique = true)
  private String id;
  private String category;
  private Description brief;
  private Description title;
  private Description content;
  private LocalDateTime date;
  private LocalDateTime updatedDate;
  private String videoLink;
  private Description videoCaption;
  private Long version;
  private Image coverImage;
  private int viewCount;
  private Collection<Image> images;

  public Image getCoverImage() {
    if(coverImage == null) {
      coverImage = new Image();
    }
    return coverImage;
  }

  public Collection<Image> getImages() {
    if(images == null) {
      images = new ArrayList<>();
    }
    return images;
  }

  public Description getVideoCaption() {
    if(videoCaption == null) {
      videoCaption = new Description();
    }
    return videoCaption;
  }

  public Description getTitle() {
    return title==null?new Description():title;
  }

  public Description getContent() {
    return content==null?new Description():content;
  }

  public Description getBrief() {
    return brief==null?new Description():brief;
  }
}
