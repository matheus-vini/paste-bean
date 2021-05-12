package br.com.inatel.icc.pastebean.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.inatel.icc.pastebean.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
