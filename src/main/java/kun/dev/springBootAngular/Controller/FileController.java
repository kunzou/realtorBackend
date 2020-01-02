package kun.dev.springBootAngular.Controller;

import kun.dev.springBootAngular.Domain.Image;
import kun.dev.springBootAngular.payload.UploadFileResponse;
import kun.dev.springBootAngular.service.FileStorageService;
import kun.dev.springBootAngular.service.ImgurService;
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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FileController {
  private static final Logger logger = LoggerFactory.getLogger(PropertyController.class);

  private FileStorageService fileStorageService;
  private ImgurService imgurService;

  public FileController(FileStorageService fileStorageService, ImgurService imgurService) {
    this.fileStorageService = fileStorageService;
    this.imgurService = imgurService;
  }

  @CrossOrigin
  @PostMapping("/uploadFile")
  public Image uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
    logger.info("Received file: " + file.getOriginalFilename());
    return imgurService.uploadImage(file);
  }

  @PostMapping("/saveFile")
  public UploadFileResponse saveFile(@RequestParam("file") MultipartFile file) {
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

  @PostMapping("/saveMultipleFiles")
  public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
    return Arrays.stream(files)
        .map(this::saveFile)
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
