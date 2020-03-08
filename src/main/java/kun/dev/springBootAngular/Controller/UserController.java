package kun.dev.springBootAngular.Controller;

import kun.dev.springBootAngular.Domain.Property;
import kun.dev.springBootAngular.Domain.User;
import kun.dev.springBootAngular.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserController {
  private static final Logger logger = LoggerFactory.getLogger(UserController.class);
  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/owner")
  @CrossOrigin
  public User getOwner() {
    logger.info("getOwner");
    return userService.getOwner();
  }

  @PutMapping("")
  @CrossOrigin
  public User updateProperty(@RequestBody User user) {
    logger.info("updateProperty" + user);
    return userService.update(user);
  }
}
