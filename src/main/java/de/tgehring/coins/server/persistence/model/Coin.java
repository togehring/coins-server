package de.tgehring.coins.server.persistence.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "coins")
public class Coin
{
	@Id
    @GeneratedValue
    private Integer id;
	
	@OneToOne
	private Country country;
	
	private double value;
	
	private int year;
	
	private String letter;
	
	private boolean commemorative;
	
	private String description;
	
	@Column(name = "created_at")
	private Date createdAt;
	
	public Coin() {
		createdAt = new Date();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

	public boolean isCommemorative() {
		return commemorative;
	}

	public void setCommemorative(boolean commemorative) {
		this.commemorative = commemorative;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
