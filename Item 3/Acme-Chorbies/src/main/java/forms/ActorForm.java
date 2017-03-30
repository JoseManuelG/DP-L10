
package forms;

import java.util.Date;

import security.UserAccount;

public class ActorForm {

	private String		typeOfActor;
	private String		confirmPassword;
	private String		name;
	private String		surname;
	private String		email;
	private String		phone;
	private String		picture;
	private String		description;
	private String		desiredRelationship;
	private String		genre;
	private Date		birthDate;
	private Boolean		acepted;
	private UserAccount	userAccount;


	//Constructor
	public ActorForm() {
		super();
	}
	//attributes------------

	public String getTypeOfActor() {
		return this.typeOfActor;
	}

	public void setTypeOfActor(final String typeOfActor) {
		this.typeOfActor = typeOfActor;
	}

	public String getConfirmPassword() {
		return this.confirmPassword;
	}

	public void setConfirmPassword(final String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getSurname() {
		return this.surname;
	}
	public void setSurname(final String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return this.email;
	}
	public void setEmail(final String email) {
		this.email = email;
	}

	public String getPhone() {
		return this.phone;
	}
	public void setPhone(final String phone) {
		this.phone = phone;
	}

	public Boolean getAcepted() {
		return this.acepted;
	}
	public void setAcepted(final Boolean acepted) {
		this.acepted = acepted;
	}

	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(final UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public String getPicture() {
		return this.picture;
	}

	public void setPicture(final String picture) {
		this.picture = picture;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getDesiredRelationship() {
		return this.desiredRelationship;
	}

	public void setDesiredRelationship(final String desiredRelationship) {
		this.desiredRelationship = desiredRelationship;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(final Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getGenre() {
		return this.genre;
	}

	public void setGenre(final String genre) {
		this.genre = genre;
	}

}
