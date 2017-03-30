
package forms;

import java.sql.Time;

import org.springframework.format.annotation.DateTimeFormat;

public class ConfigurationForm {

	private Time	cachedTime;


	// Constructor-------------------------------------
	public ConfigurationForm() {
		super();
	}
	// Attributes--------------------------------------
	@DateTimeFormat(pattern = "HH:mm:ss")
	public Time getCachedTime() {
		return this.cachedTime;
	}

	public void setCachedTime(final Time cachedTime) {
		this.cachedTime = cachedTime;
	}
}
