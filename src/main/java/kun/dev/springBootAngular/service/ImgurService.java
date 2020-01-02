package kun.dev.springBootAngular.service;

import kun.dev.springBootAngular.Domain.Image;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

@Service
public class ImgurService {
  public static final String IMGUR_API = "https://api.imgur.com/3/image";
  private RestTemplate restTemplate;

  public ImgurService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public Image uploadImage(MultipartFile file) throws Exception{
    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.AUTHORIZATION,"Client-ID c4327417d5535eb");
    headers.add(HttpHeaders.CONTENT_TYPE, MediaType.MULTIPART_FORM_DATA_VALUE);
    String encodedString = Base64.getEncoder().withoutPadding().encodeToString(file.getBytes());
    HttpEntity<String> request = new HttpEntity<>(encodedString, headers);

    String response = restTemplate.postForObject(IMGUR_API, request, String.class);
    String imageLink = getLinkFromResponse(response);

    return createImage(imageLink);
  }

  String getLinkFromResponse(String response) {
    JSONObject obj = new JSONObject(response);
    return obj.getJSONObject("data").getString("link");
  }

  Image createImage(String link) {
    return new Image(
      link,
      appendSuffixToImage(link, "t"),
      appendSuffixToImage(link, "m"),
      appendSuffixToImage(link, "l")
    );
  }

  String appendSuffixToImage(String link, String suffix) {
    return FilenameUtils.getFullPath(link) + FilenameUtils.getBaseName(link) + suffix + "." + FilenameUtils.getExtension(link);
  }
}
