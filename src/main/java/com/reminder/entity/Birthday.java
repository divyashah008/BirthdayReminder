package com.reminder.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity()
@Table(name = "birthdays")
public class Birthday {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false)
	private String name;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;

	@Column(nullable = false)
	private String email;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Birthday(Long id, String name, Date date, String email) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.email = email;
	}

	public Birthday() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Birthday [id=" + id + ", name=" + name + ", date=" + date + ", email=" + email + "]";
	}

}
