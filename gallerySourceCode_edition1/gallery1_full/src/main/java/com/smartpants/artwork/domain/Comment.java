package com.smartpants.artwork.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * Created by IntelliJ IDEA. Author: Paul T. Fisher User: paul Date: Feb 1, 2006
 * Time: 7:22:45 PM ©Copyright 2005, SmartPants Media, Inc. All Rights Reserved.
 */
@SuppressWarnings("serial")
@Entity
public class Comment implements DomainObject{
    private Long id;
	private String comment;
	private ArtEntity commentedArt;
	private Date commentDate;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String telephone;

	private Integer version;

	public Comment() {
	}
    
    @Id
    @GeneratedValue
    public final Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@ManyToOne()
    public ArtEntity getCommentedArt() {
		return commentedArt;
	}

	public void setCommentedArt(ArtEntity commentedArt) {
		this.commentedArt = commentedArt;
	}

	@Temporal(value = TemporalType.DATE)
	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Version
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
