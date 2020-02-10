package kun.dev.springBootAngular.Configuration;

import kun.dev.springBootAngular.converter.StringToDescriptionConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class MongoTemplateConfig {

  private StringToDescriptionConverter stringToDescriptionConverter;

  public MongoTemplateConfig(StringToDescriptionConverter stringToDescriptionConverter) {
    this.stringToDescriptionConverter = stringToDescriptionConverter;
  }

  @Bean
  StringToDescriptionConverter getStringToDescriptionConverter() {
    return new StringToDescriptionConverter();
  }

	@Bean
	public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory, MongoMappingContext context) {
    MongoCustomConversions conversions = new MongoCustomConversions(Arrays.asList(stringToDescriptionConverter));
		MappingMongoConverter converter =
				new MappingMongoConverter(new DefaultDbRefResolver(mongoDbFactory), context);
		converter.setTypeMapper(new DefaultMongoTypeMapper(null));
		converter.setCustomConversions(conversions);

    converter.afterPropertiesSet();
		return new MongoTemplate(mongoDbFactory, converter);
	}
}
