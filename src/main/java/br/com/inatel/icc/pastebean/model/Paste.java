package br.com.inatel.icc.pastebean.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Paste {

	private static long idCount = 0;
	@Id
	private long id;
	@ManyToOne
	private User user;
	private String title;
	private String content;
	@Enumerated(EnumType.STRING)
	private PastePrivacy privacy;

	// Blank constructor
	public Paste() {}
	
	// Constructor without assigned user
	public Paste(PastePrivacy privacy, String title, String content) {
		setId(++idCount);
		this.privacy = privacy;
		this.title = title;
		this.content = content;
		this.user = null;
	}
	
	// Constructor with assigned user
	public Paste(PastePrivacy privacy, String title, String content, User user) {
		setId(++idCount);
		this.privacy = privacy;
		this.title = title;
		this.content = content;
		this.user = user;
	}
	
	public long getId() {
		return id;
	}

	private void setId(long id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public PastePrivacy getPrivacy() {
		return privacy;
	}

}
