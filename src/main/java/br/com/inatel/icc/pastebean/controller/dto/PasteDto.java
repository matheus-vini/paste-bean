package br.com.inatel.icc.pastebean.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.inatel.icc.pastebean.model.Paste;

public class PasteDto {

	private long id;
	private UserDto user = null;
	private String title;
	private String content;

	public PasteDto (Paste paste) {
		this.id = paste.getId();
		this.title = paste.getTitle();
		this.content = paste.getContent();
		this.user = new UserDto(paste.getUser());
	}
	
	public long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public String getTitle() {
		return title;
	}

	public UserDto getUser() {
		return user;
	}

	public static List<PasteDto> convert(List<Paste> pastes) {
		return pastes.stream().map(PasteDto::new).collect(Collectors.toList());
	}

}
