package kun.dev.springBootAngular.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ImgurRestTemplate {
  @Bean
  public RestTemplate getImgurRestTemplate() {
    SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
    factory.setConnectTimeout(3000);
    factory.setReadTimeout(3000);
    RestTemplate restTemplate = new RestTemplate(factory);
    return restTemplate;
  }


}
