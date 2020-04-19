package kun.dev.springBootAngular.service

import org.junit.Test

import java.time.LocalDateTime
import java.time.Period

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
  private UserService service;

  @Test
  void testIsOverTwoMonths() {
    service = new UserService();

    assertTrue(service.isOverTwoMonths(LocalDateTime.of(2020,1,19, 0,0)));
  }
}
