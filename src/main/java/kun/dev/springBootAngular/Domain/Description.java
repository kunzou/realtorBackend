package kun.dev.springBootAngular.Domain;

import org.apache.commons.lang3.StringUtils;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Description {
	String english;
	String chinese;

	public Description() {
	}

	public Description(String chinese, String english) {
		this.chinese = chinese;
		this.english = english;
	}

	public String getEnglish() {
		if(StringUtils.isEmpty(english)) {
			english = chinese;
		}
		return english;
	}
}
