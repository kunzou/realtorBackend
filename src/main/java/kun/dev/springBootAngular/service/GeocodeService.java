package kun.dev.springBootAngular.service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeocodeService {
  private static final Logger logger = LoggerFactory.getLogger(GeocodeService.class);
  public LatLng getGeocode(String address) {

    LatLng location = new LatLng();

    GeoApiContext context = new GeoApiContext.Builder()
        .apiKey(System.getenv("GMAP_KEY"))
        .build();
    try {
      GeocodingResult[] results = GeocodingApi.newRequest(context).address(address + "Winnipeg, MB").await();
      location = results[0].geometry.location;
    } catch (Exception e) {
      logger.error(e.getMessage());
    } //todo handle exception

    return location;
  }
}
