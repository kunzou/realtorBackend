package kun.dev.springBootAngular.service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeocodeService {
  public LatLng getGeocode(String address) {

    LatLng location = new LatLng();

    GeoApiContext context = new GeoApiContext.Builder()
        .apiKey("*****")
        .build();
    try {
      GeocodingResult[] results = GeocodingApi.newRequest(context).address(address + "Winnipeg, MB").await();
      location = results[0].geometry.location;
    } catch (Exception e) {
      System.out.println(e.getMessage());
    } //todo handle exception

    return location;
  }
}
