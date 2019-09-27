package com.profile.login.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;




@Entity
@Table
public class Painting {

	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)	
	private int PaintingId;
	
	@ManyToOne(fetch= FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="artists",nullable=false)
	private Artist artists;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="painting")
	private Set<Message> message = new HashSet<Message>(0);
	
	@NotEmpty(message="Please provide Painting Name")
	@Column(unique=true)
	private String Name;
	
	@NotEmpty(message="Please provide Painting length")
	@Column
	private String length;
	
	@NotEmpty(message="Please provide Painting kind")
	@Column
	private String kind;
	
	
	@Lob
	private byte[] image;
	
	@Transient
	private String encodedImage;


	
	public String getEncodedImage() {
		return this.encodedImage;
	}
	
	public void setEncodedImage(String encodedImage) {
		this.encodedImage=encodedImage;
	}
	
	public byte[] getImage() {
		return this.image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	public void setPaintingId(int paintingId) {
		this.PaintingId=paintingId;
	}
	
	public int getPaintingId() {
		return PaintingId;
	}
	
	public Artist getArtists() {
        return artists;
    }

    public void setArtists(Artist artists) {
        this.artists = artists;
    }
	
	public void setName(String Name) {
		this.Name=Name;
	}
	
	public String getName() {
		return this.Name;
	}
	
	public void setLength(String length) {
		this.length=length;
	}
	
	public String getLength() {
		return length;
	}
	
	public void setKind(String kind) {
		this.kind=kind;
	}
	
	public String getKind() {
		return kind;
	}	
	
	public void setMessage(Set<Message> message) {
		this.message=message;
	}

	public Set<Message> getMessage(){
		return this.message;
	}
}
