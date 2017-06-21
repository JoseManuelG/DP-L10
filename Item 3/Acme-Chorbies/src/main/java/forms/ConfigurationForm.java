
package forms;

import org.hibernate.validator.constraints.Range;

public class ConfigurationForm {

	private int	hours;
	private int	minutes;
	private int	seconds;


	// Constructor-------------------------------------
	public ConfigurationForm() {
		super();
	}
	// Attributes--------------------------------------

	@Range(min = 0, max = 250)
	public int getHours() {
		return this.hours;
	}

	public void setHours(final int hours) {
		this.hours = hours;
	}

	@Range(min = 0, max = 59)
	public int getMinutes() {
		return this.minutes;
	}

	public void setMinutes(final int minutes) {
		this.minutes = minutes;
	}

	@Range(min = 0, max = 59)
	public int getSeconds() {
		return this.seconds;
	}

	public void setSeconds(final int seconds) {
		this.seconds = seconds;
	}

}
