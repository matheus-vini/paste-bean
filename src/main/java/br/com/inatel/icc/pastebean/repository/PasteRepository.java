package br.com.inatel.icc.pastebean.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.inatel.icc.pastebean.model.Paste;

public interface PasteRepository extends JpaRepository<Paste, Long> {
}
