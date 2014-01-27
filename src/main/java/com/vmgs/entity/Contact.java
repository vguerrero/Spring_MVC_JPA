package com.vmgs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import com.vmgs.entity.Category;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.FetchType;
import javax.persistence.NamedQuery;

@Entity
@Table(name = "contact")
@NamedQuery(name=Contact.FIND_ALL, query="SELECT p FROM Contact p") //estas consultas estaticas son eficientes
public class Contact {
	
	public static final String FIND_ALL = "Contact.findAll"; 

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "FIRSTNAME")
	@Size(min=2, max=30) 
	private String firstname;

	@Column(name = "LASTNAME")
	@Size(min=2, max=30) 
	private String lastname;

	@Column(name = "EMAIL")
	@Email
	@NotEmpty
	private String email;

	@Column(name = "TELEPHONE")
	@NotEmpty	
	private String telephone;
	
	@OneToOne(fetch=FetchType.EAGER) //LAZY lectura demorada // optional=false--> no nulos
	@JoinColumn(name="Category_FK")
	private Category category;

	public String getEmail() {
		return email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}
	
	public String getCompleteName(){
		return this.firstname + " " + this.lastname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	public String toString() {
		return "Contact [firstname=" + firstname + ", lastname=" + lastname + ", Category= " 
				+ "]";
	}

}
