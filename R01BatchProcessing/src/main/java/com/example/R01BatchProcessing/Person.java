package com.example.R01BatchProcessing;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
 
@Entity
public class Person{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ID;
	String Name;
	String Email;
	String Phone;
	String Address;
	int Age;
	String Salary;
	
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public int getAge() {
		return Age;
	}
	public void setAge(int age) {
		Age = age;
	}
	public String getSalary() {
		return Salary;
	}
	public void setSalary(String salary) {
		Salary = salary;
	}
	
	@Override
	public String toString() {
		return "Person [ID=" + ID + ", Name=" + Name + ", Email=" + Email + ", Phone=" + Phone + ", Address=" + Address
				+ ", Age=" + Age + ", Salary=" + Salary + "]";
	}
	public Person(Long iD, String name, String email, String phone, String address, int age, String salary) {
		super();
		ID = iD;
		Name = name;
		Email = email;
		Phone = phone;
		Address = address;
		Age = age;
		Salary = salary;
	}
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}