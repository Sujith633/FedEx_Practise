package com.example.S02SpringBatchCSV;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Person{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
	String name;
	String email;
	String address;
	String phonenum;
	int age;
	String salary;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", email=" + email + ", address=" + address + ", phonenum="
				+ phonenum + ", age=" + age + ", salary=" + salary + "]";
	}
	public Person(Long id, String name, String email, String address, String phonenum, int age, String salary) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.address = address;
		this.phonenum = phonenum;
		this.age = age;
		this.salary = salary;
	}
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
