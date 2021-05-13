package br.com.inatel.icc.pastebean.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.inatel.icc.pastebean.controller.UserSearch;
import br.com.inatel.icc.pastebean.model.Paste;
import br.com.inatel.icc.pastebean.model.PastePrivacy;
import br.com.inatel.icc.pastebean.model.User;

public class PasteForm {

	private long id;
	private User user;
	@NotNull @NotEmpty @Size(min = 3)
	private String title;
	@NotNull @NotEmpty
	private String content;
	private PastePrivacy privacy;
	private long userId = 0;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public PastePrivacy getPrivacy() {
		return privacy;
	}

	public void setPrivacy(PastePrivacy privacy) {
		this.privacy = privacy;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public Paste convert(UserSearch map) {
		if(userId == 0) {
			return new Paste(privacy, title, content);
		}
		user = map.findUser(userId);
		return new Paste(privacy, title, content, user);
	}

}
