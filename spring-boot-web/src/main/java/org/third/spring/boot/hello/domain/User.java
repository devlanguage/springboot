package org.third.spring.boot.hello.domain;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author zh
 * @ClassName cn.saytime.bean.User
 * @Description
 */
@XmlRootElement
public class User {

	private Long id;
	private String name;
	private int age;
	private Date ctm;
	AtomicLong randomer = new AtomicLong(1000);

	public User() {
		id = randomer.getAndIncrement();
	}

	public User(String name, int age) {
		id = randomer.getAndIncrement();

		this.name = name;
		this.age = age;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getCtm() {
		return ctm;
	}

	public void setCtm(Date ctm) {
		this.ctm = ctm;
	}

}