package br.com.inatel.icc.pastebean.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

	private static long idCount = 0;
	@Id
	private long id;
	private String username;
	private String password;
	@ElementCollection
	private List<Paste> userPasteList;

	// Blank constructor
	public User() {}
	
	public User(String username, String password) {
		setId(++idCount);
		userPasteList = new ArrayList<Paste>();
		this.username = username;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	private void setId(long id) {
		this.id = id;
	}

	public List<Paste> getUserPasteList() {
		return userPasteList;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}
