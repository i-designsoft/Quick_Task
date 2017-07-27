package com.bridgeit.todoApplication.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GmailProfile {
	private String id;
	private List<GmailId> emails;
	private String displayName;
	private GmailImage image;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<GmailId> getEmails() {
		return emails;
	}
	public void setEmails(List<GmailId> emails) {
		this.emails = emails;
	}
	
	public GmailImage getImage() {
		return image;
	}
	public void setImage(GmailImage image) {
		this.image = image;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}
