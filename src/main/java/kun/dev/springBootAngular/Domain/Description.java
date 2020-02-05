package kun.dev.springBootAngular.Domain;

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
}
