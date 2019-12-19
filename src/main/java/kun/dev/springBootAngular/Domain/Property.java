package kun.dev.springBootAngular.Domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
    private HouseType houseType;
    private Integer numberOfRooms;
    private Integer numberOfWashrooms;
    private Double area;
    private BigDecimal askingPrice;
    private BigDecimal finalPrice;
    private LocalDate onMarketSince;
    private LocalDate dealDate;

    public Property() {
    }

    public Property(@NonNull String address) {
        this.address = address;
    }
}
