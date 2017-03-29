
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class SearchTemplate extends DomainEntity {

	// Attributes -------------------------------------------------------------

	private String	desiredRelationship;
	private Integer	aporxAge;
	private String	genre;
	private String	keyword;
	private Date	cacheMoment;


	@Pattern(regexp = "^$|^activities$|^friendship$|^love$")
	public String getDesiredRelationship() {
		return this.desiredRelationship;
	}

	public void setDesiredRelationship(final String desiredRelationship) {
		this.desiredRelationship = desiredRelationship;
	}

	public Integer getAporxAge() {
		return this.aporxAge;
	}

	public void setAporxAge(final Integer aporxAge) {
		this.aporxAge = aporxAge;
	}

	@Pattern(regexp = "^$^man$|^woman$")
	public String getGenre() {
		return this.genre;
	}

	public void setGenre(final String genre) {
		this.genre = genre;
	}

	@NotNull
	public String getKeyword() {
		return this.keyword;
	}

	public void setKeyword(final String keyword) {
		this.keyword = keyword;
	}

	@NotNull
	@Past
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCacheMoment() {
		return this.cacheMoment;
	}

	public void setCacheMoment(final Date cacheMoment) {
		this.cacheMoment = cacheMoment;
	}


	// Relationships ----------------------------------------------------------

	private Coordinates			coordinates;
	private Collection<Chorbi>	chorbies;


	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Coordinates getCoordinates() {
		return this.coordinates;
	}

	public void setCoordinates(final Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	@Valid
	@NotNull
	@ManyToMany
	public Collection<Chorbi> getChorbies() {
		return this.chorbies;
	}

	public void setChorbies(final Collection<Chorbi> chorbies) {
		this.chorbies = chorbies;
	}

}
