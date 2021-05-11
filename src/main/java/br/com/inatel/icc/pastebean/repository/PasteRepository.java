package br.com.inatel.icc.pastebean.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.inatel.icc.pastebean.model.Paste;
import br.com.inatel.icc.pastebean.model.PastePrivacy;

public interface PasteRepository extends JpaRepository<Paste, Long> {

	List<Paste> findByPrivacy(PastePrivacy privacy);
}
