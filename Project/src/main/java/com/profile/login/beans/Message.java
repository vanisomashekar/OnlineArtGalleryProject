package com.profile.login.beans;

import javax.persistence.*;


import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name = "Message")
public class Message {
	
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int messageId;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="painting",nullable=false)
	private Painting painting;
	
	@ManyToOne(fetch= FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="customer",nullable=false)
    private Customer customer;
    
	@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="artist",nullable=false)
    private Artist artist;
    
	
	@NotEmpty(message="Please provide message to send")
	@Column(unique=true)
    private String message;
	
	
	@Column
    private String messageDate;

	
	@Column
    private String sentBy;
    
	@Transient
	private String search;
	
	public String getSearch() {
		return this.search;
	}
	
	public void setSearch(String search) {
		this.search=search;
	}
    
    public String getSentBy() {
    	return this.sentBy;
    }
    
    public void setSentBy(String sentBy) {
    	this.sentBy=sentBy;
    }
    
	public int getMessageId() {
        return this.messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Painting getPainting() {
    	return this.painting;
    }
    
    public void setPainting(Painting painting) {
    	this.painting=painting;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageDate() {
        return this.messageDate;
    }

    public void setMessageDate(String messageDate) {
        this.messageDate = messageDate;
    }

    public Artist getArtist() {
        return this.artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
    
}
