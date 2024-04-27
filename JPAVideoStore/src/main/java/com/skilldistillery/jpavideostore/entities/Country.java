package com.skilldistillery.jpavideostore.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Country {
	
	@Id
	@Column(name = "country_code")
	private String countryCode;
	
	@Column(name = "country")
	private String name;

	public Country() {
		super();
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(countryCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		return Objects.equals(countryCode, other.countryCode);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Country [countryCode=").append(countryCode).append(", name=").append(name).append("]");
		return builder.toString();
	}

}
