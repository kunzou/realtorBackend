package kun.dev.springBootAngular.converter;

import kun.dev.springBootAngular.Domain.Description;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToDescriptionConverter implements Converter<String, Description> {
  @Override
  public Description convert(String s) {
    JSONObject obj;
    Description description;
    try {
      obj = new JSONObject(s);
      description = new Description(obj.getString("chinese"), obj.getString("english"));
    } catch (JSONException e){
      description = new Description();
    }
    return description;
  }
}
