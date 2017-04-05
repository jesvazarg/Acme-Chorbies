
package forms;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import domain.Coordinate;
import domain.CreditCard;

public class CreateChorbiForm {

	private String		username;
	private String		password;
	private String		confirmPassword;
	private String		name;
	private String		surname;
	private String		email;
	private String		phone;
	private String		picture;
	private String		description;
	private String		relationship;
	private Date		birthDate;
	private String		genre;
	private Coordinate	coordinate;
	private CreditCard	creditCard;
	private Boolean		ban;


	@NotBlank
	@Size(min = 5, max = 32)
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	@NotBlank
	@Size(min = 5, max = 32)
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	@NotBlank
	@Size(min = 5, max = 32)
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	public String getConfirmPassword() {
		return this.confirmPassword;
	}

	public void setConfirmPassword(final String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	public String getSurname() {
		return this.surname;
	}

	public void setSurname(final String surname) {
		this.surname = surname;
	}

	@NotBlank
	@Email
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	@NotBlank
	@Pattern(regexp = "(\\+\\d{1,3} )?(\\(\\d{1,3}\\) )?(\\w{4,})")
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(final String phone) {
		this.phone = phone;
	}

	@NotBlank
	@URL
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	public String getPicture() {
		return this.picture;
	}
	public void setPicture(final String picture) {
		this.picture = picture;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	public String getDescription() {
		return this.description;
	}
	public void setDescription(final String description) {
		this.description = description;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	public String getRelationship() {
		return this.relationship;
	}
	public void setRelationship(final String relationship) {
		this.relationship = relationship;
	}

	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getBirthDate() {
		return this.birthDate;
	}
	public void setBirthDate(final Date birthDate) {
		this.birthDate = birthDate;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	public String getGenre() {
		return this.genre;
	}
	public void setGenre(final String genre) {
		this.genre = genre;
	}

	@NotNull
	@Valid
	public Coordinate getCoordinate() {
		return this.coordinate;
	}
	public void setCoordinate(final Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	@Valid
	public CreditCard getCreditCard() {
		return this.creditCard;
	}
	public void setCreditCard(final CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	@NotNull
	public Boolean getBan() {
		return this.ban;
	}
	public void setBan(final Boolean ban) {
		this.ban = ban;
	}

}
