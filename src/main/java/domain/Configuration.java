
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Configuration extends DomainEntity {

	// Constructors ----------------------------------------------------------
	public Configuration() {
		super();
	}


	// Attributes -------------------------------------------------------------
	private String				time;
	private Collection<String>	banner;


	@NotBlank
	@Pattern(regexp = "\\d{2}\\:\\d{2}\\:\\{2})?")
	public String getTime() {
		return this.time;
	}

	public void setTime(final String time) {
		this.time = time;
	}

	@NotBlank
	@ElementCollection
	public Collection<String> getBanner() {
		return this.banner;
	}

	public void setBanner(final Collection<String> banner) {
		this.banner = banner;
	}

	// Relationships ----------------------------------------------------------

}
