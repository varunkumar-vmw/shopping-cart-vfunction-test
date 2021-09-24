package com.tanzu.vfunction.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue
	private int userId;
	private String name;
	private String address;
	private Date memberSince;

	public User() {
	}

	public User(final String name, final String address, final Date memberSince) {
		super();
		this.name = name;
		this.address = address;
		this.memberSince = memberSince;
	}

	public int getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	public Date getMemberSince() {
		return memberSince;
	}

	public void setMemberSince(final Date memberSince) {
		this.memberSince = memberSince;
	}

}
