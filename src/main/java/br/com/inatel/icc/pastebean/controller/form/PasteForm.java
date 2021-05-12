package br.com.inatel.icc.pastebean.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.inatel.icc.pastebean.controller.dto.UserDto;
import br.com.inatel.icc.pastebean.model.Paste;
import br.com.inatel.icc.pastebean.model.PastePrivacy;

public class PasteForm {

	private long id;
	private UserDto user;
	@NotNull @NotEmpty @Size(min = 3)
	private String title;
	@NotNull @NotEmpty
	private String content;
	private PastePrivacy privacy;

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

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
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

	public Paste convert() {
		return new Paste(privacy, title, content);
	}

}
