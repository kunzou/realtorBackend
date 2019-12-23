package kun.dev.springBootAngular.Domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.google.maps.model.LatLng;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Property {
    @Id
    private String id;
    private @NonNull String address;
    private String primaryImgUrl;
    private List<String> imgUrls;
    private String propertyType;
    private String houseStatus;
    private Integer yearBuilt;
    private String description;
    private Integer numberOfRooms;
    private Integer numberOfWashrooms;
    private Double area;
    private BigDecimal askingPrice;
    private BigDecimal finalPrice;
    private LocalDate onMarketSince;
    private LocalDate dealDate;
    private boolean hide;
    private LatLng location;
    private Long version;

    public Property() {
    }

    public Property(@NonNull String address) {
        this.address = address;
    }

    public List<String> getImgUrls() {
        if(imgUrls == null) {
            imgUrls = new ArrayList<>();
        }
        return imgUrls;
    }
}
