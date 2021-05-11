package br.com.inatel.icc.pastebean.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.inatel.icc.pastebean.controller.dto.PasteDto;
import br.com.inatel.icc.pastebean.controller.dto.PasteFormDto;
import br.com.inatel.icc.pastebean.model.Paste;
import br.com.inatel.icc.pastebean.repository.PasteRepository;

@RestController
@RequestMapping("pastes")
public class PasteController {
	
	@Autowired
	private PasteRepository pasteRepository;
	
	@PostMapping
	public ResponseEntity<PasteDto> createPaste(@RequestBody PasteFormDto pasteForm, UriComponentsBuilder uriBuilder) {
		Paste paste = pasteForm.convert();
		pasteRepository.save(paste);
		
		URI uri = uriBuilder.path("Topico/{id}").buildAndExpand(paste.getId()).toUri();
		return ResponseEntity.created(uri).body(new PasteDto(paste));
	}
	
	@GetMapping
	public List<PasteDto> readPastes() {
		List<Paste> pastes = pasteRepository.findAll();
		return PasteDto.convert(pastes);
	}
}
