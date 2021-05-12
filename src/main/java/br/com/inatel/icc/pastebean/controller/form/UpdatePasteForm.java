package br.com.inatel.icc.pastebean.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.inatel.icc.pastebean.model.Paste;
import br.com.inatel.icc.pastebean.repository.PasteRepository;

public class UpdatePasteForm {

	@NotNull @NotEmpty @Size(min = 3)
	private String title;
	@NotNull @NotEmpty
	private String content;

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

	public Paste update(Long id, PasteRepository pasteRepository) {
		Paste paste = pasteRepository.getOne(id);
		paste.setTitle(this.title);
		paste.setContent(this.content);
		
		return paste;
	}

}
