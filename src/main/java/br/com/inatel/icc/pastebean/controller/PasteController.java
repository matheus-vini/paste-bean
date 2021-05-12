package br.com.inatel.icc.pastebean.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.inatel.icc.pastebean.controller.dto.PasteDto;
import br.com.inatel.icc.pastebean.controller.form.PasteForm;
import br.com.inatel.icc.pastebean.controller.form.UpdatePasteForm;
import br.com.inatel.icc.pastebean.model.Paste;
import br.com.inatel.icc.pastebean.model.PastePrivacy;
import br.com.inatel.icc.pastebean.repository.PasteRepository;

@RestController
@RequestMapping("pastes")
public class PasteController {
	
	@Autowired
	private PasteRepository pasteRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<PasteDto> createPaste(@RequestBody @Valid PasteForm pasteForm, UriComponentsBuilder uriBuilder) {
		Paste paste = pasteForm.convert();
		pasteRepository.save(paste);
		
		URI uri = uriBuilder.path("pastes/{id}").buildAndExpand(paste.getId()).toUri();
		return ResponseEntity.created(uri).body(new PasteDto(paste));
	}
	
	@GetMapping
	public List<PasteDto> readPastes() {
		List<Paste> pastes = pasteRepository.findByPrivacy(PastePrivacy.PUBLIC);
		return PasteDto.convert(pastes);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<PasteDto> readPastes(@PathVariable Long id) {
		Optional<Paste> paste = pasteRepository.findById(id);
		if(paste.isPresent()) {
			return ResponseEntity.ok(new PasteDto(paste.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("{id}")
	@Transactional
	public ResponseEntity<PasteDto> updatePastes(@PathVariable Long id, @RequestBody @Valid UpdatePasteForm pasteForm) {
		Optional<Paste> optional = pasteRepository.findById(id);
		if(optional.isPresent()) {
			Paste paste = pasteForm.update(id, pasteRepository);
			return ResponseEntity.ok(new PasteDto(paste));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("{id}")
	@Transactional
	public ResponseEntity<?> deletePastes(@PathVariable Long id) {
		Optional<Paste> optional = pasteRepository.findById(id);
		if(optional.isPresent()) {
			pasteRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
}
