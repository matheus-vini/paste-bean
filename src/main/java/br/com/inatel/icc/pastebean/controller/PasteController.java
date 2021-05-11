package br.com.inatel.icc.pastebean.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.inatel.icc.pastebean.controller.dto.PasteDto;
import br.com.inatel.icc.pastebean.controller.dto.form.PasteFormDto;
import br.com.inatel.icc.pastebean.controller.dto.form.UpdatePasteFormDto;
import br.com.inatel.icc.pastebean.model.Paste;
import br.com.inatel.icc.pastebean.model.PastePrivacy;
import br.com.inatel.icc.pastebean.repository.PasteRepository;

@RestController
@RequestMapping("pastes")
public class PasteController {
	
	@Autowired
	private PasteRepository pasteRepository;
	
	@PostMapping
	public ResponseEntity<PasteDto> createPaste(@RequestBody @Valid PasteFormDto pasteForm, UriComponentsBuilder uriBuilder) {
		Paste paste = pasteForm.convert();
		pasteRepository.save(paste);
		
		URI uri = uriBuilder.path("Topico/{id}").buildAndExpand(paste.getId()).toUri();
		return ResponseEntity.created(uri).body(new PasteDto(paste));
	}
	
	@GetMapping
	public List<PasteDto> readPastes() {
		List<Paste> pastes = pasteRepository.findByPrivacy(PastePrivacy.PUBLIC);
		return PasteDto.convert(pastes);
	}
	
	@GetMapping("{id}")
	public PasteDto readPastes(@PathVariable Long id) {
		Paste paste = pasteRepository.getOne(id);
		return new PasteDto(paste);
	}
	
	@PutMapping("{id}")
	@Transactional
	public ResponseEntity<PasteDto> updatePastes(@PathVariable Long id, @RequestBody @Valid UpdatePasteFormDto pasteForm) {
		Paste paste = pasteForm.update(id, pasteRepository);
		
		return ResponseEntity.ok(new PasteDto(paste));
	}
}
