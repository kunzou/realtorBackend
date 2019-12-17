package kun.dev.springBootAngular;

import kun.dev.springBootAngular.payload.UploadFileResponse;
import kun.dev.springBootAngular.service.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class HeroController {
  private static final Logger logger = LoggerFactory.getLogger(HeroController.class);

  private HeroRepository repository;
  private FileStorageService fileStorageService;

  public HeroController(HeroRepository repository, FileStorageService fileStorageService) {
    this.repository = repository;
    this.fileStorageService = fileStorageService;
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
        .filter(hero -> hero.getName().contains(name.trim()))
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

  @PostMapping("/uploadFile")
  public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
    logger.info("Received file: " + file.getOriginalFilename());
    String fileName = fileStorageService.storeFile(file);

    String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
        .path("/downloadFile/")
        .path(fileName)
        .toUriString();

    logger.info("File: " + file.getName() + "is available at" + fileDownloadUri);

    return new UploadFileResponse(fileName, fileDownloadUri,
        file.getContentType(), file.getSize());
  }

  @PostMapping("/uploadMultipleFiles")
  public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
    return Arrays.asList(files)
        .stream()
        .map(file -> uploadFile(file))
        .collect(Collectors.toList());
  }

  @GetMapping("/downloadFile/{fileName:.+}")
  public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
    // Load file as Resource
    Resource resource = fileStorageService.loadFileAsResource(fileName);

    // Try to determine file's content type
    String contentType = null;
    try {
      contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
    } catch (IOException ex) {
      logger.info("Could not determine file type.");
    }

    // Fallback to the default content type if type could not be determined
    if(contentType == null) {
      contentType = "application/octet-stream";
    }

    return ResponseEntity.ok()
        .contentType(MediaType.parseMediaType(contentType))
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
        .body(resource);
  }

}
