package com.example.api.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Class to model the products.
 */
@Entity
@Table(name = "PRODUCT")
public class Product implements Serializable {

	/**
	 * Generated serial version.
	 */
	private static final long serialVersionUID = 1027206179193371480L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "AVAILABLE")
	private Boolean available;

	@Column(name = "PRICE")
	private Float price;

	@Column(name = "DESCRIPTION")
	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_CREATED", nullable = false, insertable = true, updatable = false)
	private Date dateCreated = new Date();

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	@Override
	public String toString() {
		return String.format("ID: %d, Name: %s, Available: %b, Price: %f, Description: %s, Creation date: %tD", id,
				name, available, price, description, dateCreated);

	}

}
