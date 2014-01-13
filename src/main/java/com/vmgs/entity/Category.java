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
import java.io.Serializable;


@Entity
@Table(name = "category")
public class Category implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Size(min=2, max=15) //validation
	@NotEmpty //validation
	@Column(name="name", nullable=false)
	private String name;
	
	public Integer getId(){ return id;	}
	public void setId(Integer id){ this.id=id;}
	
	public String getName(){ return name;	}
	public void setName(String name){ this.name=name;}
	
	public Category(){}
	
	public Category(String name){
		this.name=name;
	}
}