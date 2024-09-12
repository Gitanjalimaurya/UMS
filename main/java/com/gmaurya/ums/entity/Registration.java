package com.gmaurya.ums.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="registration")
public class Registration {
	//Column names
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "registration_id", nullable = false)
//	private Long id;

	@Id
	@Column (name = "email", nullable = false)
 	private String email;

	@Column(name="password", nullable=false)
	private String password;

	@Column(name = "profile",nullable = false)
	private String profile;
	
	@Column(name="first_name", nullable=false)
	private String first_name;
	
	@Column(name = "middle_name", nullable = false)
	private String middle_name;
	
	@Column(name="last_name", nullable=false)
	private String last_name;
	
	@Column(name = "house_no",nullable = false)
	private String house_no;
	
	@Column(name = "street_name",nullable = false)
	private String street_name;
	
	@Column(name="city", nullable=false)
	private String city;
	
	@Column(name="state", nullable=false)
	private String state;
	
	@Column(name = "country",nullable = false)
	private String country;
	
	@Column(name="pin_code", nullable=false)
	private String pin_code;
	
	@Column(name="phone_no", nullable=false)
	private String phone_no;
	
	@Column(name="salutation", nullable = false)
	private String salutation;

	@Column(name = "question_1",nullable = false)
	private String question_1;

	@Column(name = "answer_1", nullable = false)
	private String answer_1;
	
	@Column(name = "question_2",nullable = false)
	private String question_2;

	@Column(name = "answer_2",nullable = false)
	private String answer_2;
	
	@Column(name = "question_3",nullable = false)
	private String question_3;

	@Column(name = "answer_3",nullable = false)
	private String answer_3;

	public Registration(String email, String password, String profile, String first_name, String middle_name, String last_name, String house_no, String street_name, String city, String state, String country, String pin_code, String phone_no, String salutation, String question_1, String answer_1, String question_2, String answer_2, String question_3, String answer_3) {
		this.email = email;
		this.password = password;
		this.profile = profile;
		this.first_name = first_name;
		this.middle_name = middle_name;
		this.last_name = last_name;
		this.house_no = house_no;
		this.street_name = street_name;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pin_code = pin_code;
		this.phone_no = phone_no;
		this.salutation = salutation;
		this.question_1 = question_1;
		this.answer_1 = answer_1;
		this.question_2 = question_2;
		this.answer_2 = answer_2;
		this.question_3 = question_3;
		this.answer_3 = answer_3;
	}
}
