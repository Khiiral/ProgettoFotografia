package it.uniroma3.siw.fotografia.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.fotografia.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
