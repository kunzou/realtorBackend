package kun.dev.springBootAngular.Controller;

import kun.dev.springBootAngular.Domain.Property;
import kun.dev.springBootAngular.Domain.User;
import kun.dev.springBootAngular.Domain.VisitHistory;
import kun.dev.springBootAngular.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
  private static final Logger logger = LoggerFactory.getLogger(UserController.class);
  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/owner")
  public User getOwner() {
    return userService.getOwner();
  }

  @GetMapping("/visitHistory")
  public List<VisitHistory> getVisitHistory() {
    return userService.getVisitHistory();
  }

  @PutMapping("")
  public User updateUser(@RequestBody User user) {
    return userService.update(user);
  }
}
