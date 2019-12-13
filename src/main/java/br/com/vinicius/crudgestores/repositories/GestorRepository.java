package br.com.vinicius.crudgestores.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vinicius.crudgestores.models.Gestor;

public interface GestorRepository extends JpaRepository<Gestor, Long>{

}
