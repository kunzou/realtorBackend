package kun.dev.springBootAngular;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
public class HeroController {
  private HeroRepository repository;

  public HeroController(HeroRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/heroes")
  @CrossOrigin(origins = "http://localhost:4200")
  public Collection<Hero> getHeroes() {
    return new ArrayList<>(repository.findAll());
  }

  @GetMapping("/heroes/{id}")
  @CrossOrigin(origins = "http://localhost:4200")
  public Hero getHeroById(@PathVariable("id") Long id) {
    return repository.findById(id)
        .orElse(null);
  }

  @GetMapping("/heroes/")
  @CrossOrigin(origins = "http://localhost:4200")
  public Collection<Hero> getHeroByName(@RequestParam String name) {
    return repository.findAll().stream()
        .filter(hero -> hero.getName().contains(name))
        .collect(Collectors.toList());
  }

  @PostMapping("/heroes")
  @CrossOrigin(origins = "http://localhost:4200")
  public Hero addHero(@RequestBody Hero hero) {
    return repository.save(hero);
  }

  @PutMapping("/heroes")
  @CrossOrigin(origins = "http://localhost:4200")
  public Hero updateHero(@RequestBody Hero hero) {
    return repository.save(hero);
  }

  @DeleteMapping("/heroes/{id}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity deleteHero(@PathVariable("id") Long id) {
    repository.deleteById(id);
    return ResponseEntity.ok().build();
  }
}
