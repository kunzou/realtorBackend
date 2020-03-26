package kun.dev.springBootAngular.service

import org.junit.Test
import static org.junit.jupiter.api.Assertions.*;

class GeocodeServiceTest {
  private GeocodeService service;

  @Test
  public void toSearchableAddress() {
    service = new GeocodeService();

    assertEquals("110 Adams st", service.toSearchableAddress("3-110 Adams st"));
    assertEquals("110 Adams st", service.toSearchableAddress("110 Adams st"));
    assertEquals("", service.toSearchableAddress(""));
    assertEquals(null, service.toSearchableAddress(null));
  }
}
