package de.tgehring.coins.server.persistence.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "countries")
public class Country
{
	@Id
    @GeneratedValue
    private Integer id;
	
	private String code;
	
	private String name;
	
	@Column(name = "created_at")
	private Date createdAt;
	
	public Country() {
		createdAt = new Date();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}
}
