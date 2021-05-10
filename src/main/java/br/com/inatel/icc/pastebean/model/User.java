package br.com.inatel.icc.pastebean.model;

import java.util.ArrayList;
import java.util.List;

public class User {

	private static long idCount = 0; 
	private long id;
	private List<Paste> userPasteList;
	private String username;
	private String password;

	public User(String username, String password) {
		setId(++idCount);
		userPasteList = new ArrayList<Paste>();
		this.username = username;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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
