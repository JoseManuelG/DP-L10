
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

@Entity
@Access(AccessType.PROPERTY)
public class Configuration extends DomainEntity {

	// Attributes -------------------------------------------------------------

	private long	cachedTime;


	public long getCachedTime() {
		return this.cachedTime;
	}

	public void setCachedTime(final long cachedTime) {
		this.cachedTime = cachedTime;
	}

	// Relationships ----------------------------------------------------------

}
