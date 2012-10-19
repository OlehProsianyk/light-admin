package org.lightadmin.core.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.util.Assert;

import javax.persistence.Entity;

@Entity
public class Address extends AbstractEntity {

	private String street, city, country;

	public Address( String street, String city, String country ) {
		Assert.hasText( street, "Street must not be null or empty!" );
		Assert.hasText( city, "City must not be null or empty!" );
		Assert.hasText( country, "Country must not be null or empty!" );

		this.street = street;
		this.city = city;
		this.country = country;
	}

	protected Address() {

	}

	@JsonIgnore
	public Address getCopy() {
		return new Address( this.street, this.city, this.country );
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}
}