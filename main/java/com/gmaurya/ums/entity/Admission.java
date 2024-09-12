package com.gmaurya.ums.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="admission")
public class Admission {

	//Column names
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="application_form_no")
    private Long application_form_no;
	
	@Column(name="student_name", nullable=false)
	private String student_name;
	
	@Column(name="father_name", nullable=false)
	private String father_name;
	
	@Column(name="mother_name", nullable=false)
	private String mother_name;

	@Column(name = "date_of_birth", nullable = false)
	private Date date_of_birth;
	
	@Column(name = "religion")
	private String religion;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="house_no", nullable = false)
	private String house_no;
	
	@Column(name="street_name", nullable = false)
	private String street_name;
	
	@Column(name="city", nullable=false)
	private String city;
	
	@Column(name="state", nullable=false)
	private String state;
	
	@Column(name="country", nullable=false)
	private String country;
	
	@Column(name="pin_code", nullable=false)
	private String pin_code;

	@Column(name = "student_image", nullable = false)
	private byte[] student_image;
	
	@Column(name="student_phone_no", nullable=false)
	private String student_phone_no;
	
	@Column(name="parent_phone_no", nullable=false)
	private String parent_phone_no;
	
	@Column(name="student_email", nullable=false)
	private String student_email;
	
	@Column(name="parent_email", nullable=false)
	private String parent_email;
	
	@Column(name = "category", nullable = false)
	private String category;
	
	@Column(name = "high_school_board", nullable = false)
	private String high_school_board;
	
	@Column(name = "high_school_year_of_passing", nullable=false)
	private int high_school_year_of_passing;
	
	@Column(name = "high_school_marks", nullable = false)
	private float high_school_marks; 

	@Column(name = "intermediate_board", nullable = false)
	private String intermediate_board;
	
	@Column(name = "intermediate_year_of_passing", nullable=false)
	private int intermediate_year_of_passing;
	
	@Column(name = "intermediate_marks", nullable = false)
	private float intermediate_marks;

	@Column(name = "degree",nullable = false)
	private String degree;

	@Column(name = "course",nullable = false)
	private String course;

	@Column(name = "stream",nullable = false)
	private String stream;

	@Column(name = "high_school_marksheet", columnDefinition = "BYTEA", nullable = false)
	private byte[] high_school_marksheet;

	@Column(name = "intermediate_marksheet", columnDefinition = "BYTEA", nullable = false)
	private byte[] intermediate_marksheet;

	public Admission(String student_name, String father_name, String mother_name, Date date_of_birth, String religion, String gender, String house_no, String street_name, String city, String state, String country, String pin_code, byte[] student_image, String student_phone_no, String parent_phone_no, String student_email, String parent_email, String category, String high_school_board, int high_school_year_of_passing, float high_school_marks, String intermediate_board, int intermediate_year_of_passing, float intermediate_marks, String degree, String stream, String course, byte[] high_school_marksheet, byte[] intermediate_marksheet) {
		this.student_name = student_name;
		this.father_name = father_name;
		this.mother_name = mother_name;
		this.date_of_birth = date_of_birth;
		this.religion = religion;
		this.gender = gender;
		this.house_no = house_no;
		this.street_name = street_name;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pin_code = pin_code;
		this.student_image = student_image;
		this.student_phone_no = student_phone_no;
		this.parent_phone_no = parent_phone_no;
		this.student_email = student_email;
		this.parent_email = parent_email;
		this.category = category;
		this.high_school_board = high_school_board;
		this.high_school_year_of_passing = high_school_year_of_passing;
		this.high_school_marks = high_school_marks;
		this.intermediate_board = intermediate_board;
		this.intermediate_year_of_passing = intermediate_year_of_passing;
		this.intermediate_marks = intermediate_marks;
		this.degree = degree;
		this.stream = stream;
		this.course = course;
		this.high_school_marksheet = high_school_marksheet;
		this.intermediate_marksheet = intermediate_marksheet;
	}
}
