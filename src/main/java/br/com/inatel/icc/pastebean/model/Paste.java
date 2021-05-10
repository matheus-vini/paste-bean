package br.com.inatel.icc.pastebean.model;

public class Paste {

	private static long idCount = 0;
	private long id;
	private String content;
	private String title;
	private User user;
	private PastePrivacy privacy;

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

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public String getTitle() {
		return title;
	}

	public User getUser() {
		return user;
	}

	public PastePrivacy getPrivacy() {
		return privacy;
	}

}
