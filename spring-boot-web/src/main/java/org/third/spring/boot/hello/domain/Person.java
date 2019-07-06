package org.third.spring.boot.hello.domain;

import java.util.Date;

public class Person {
	private Long id;
	private String name;
	private String address;
	private int age;
	private Date ctm;

	public Person() {
		super();
	}

	public Person(String name, Integer age, String address) {
		super();
		this.name = name;
		this.age = age;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCtm() {
		return ctm;
	}

	public void setCtm(Date ctm) {
		this.ctm = ctm;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
