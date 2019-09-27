package com.profile.login.beans;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table
public class Artist {
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int c_id;

	@NotEmpty
	@Column(unique=true)
	private String c_name;

	@Email(message="Please enter valid email")
	@NotEmpty(message="Please enter email")
	@Column(unique=true)
	private String c_email;

	@Size(max=15, min=8,message= "Password must be atleast 8 characters")
	@NotEmpty(message="Please enter password")
	@Column(nullable=false)
	private String c_password;
	
	@OneToMany(targetEntity=Message.class, mappedBy = "artist",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private Set<Message> message = new HashSet<Message>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "artists" ,targetEntity=Painting.class,cascade=CascadeType.ALL)
	private Set<Painting> painting = new HashSet<Painting>(0);

	
	public void setMessage(Set<Message> message) {
		this.message=message;
	}

	public Set<Message> getMessage(){
		return this.message;
	}
	
	
	public void setPainting(Set<Painting> painting) {
		this.painting=painting;
	}

	public Set<Painting> getPainting(){
		return this.painting;
	}

	public void setC_id(int c_id) {
		this.c_id=c_id;
	}

	public int getC_id() {
		return this.c_id;
	}
	public void setC_password(String c_password) {
		this.c_password=c_password;
	}

	public String getC_password() {
		return this.c_password;
	}
	public void setC_name(String c_name) {
		this.c_name=c_name;
	}

	public String getC_name() {
		return this.c_name;
	}
	public void setC_email(String c_email) {
		this.c_email=c_email;
	}

	public String getC_email() {
		return this.c_email;
	}
}
