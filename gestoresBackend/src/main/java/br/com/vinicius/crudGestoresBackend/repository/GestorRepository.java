package br.com.vinicius.crudGestoresBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.vinicius.crudGestoresBackend.model.Gestor;

@Repository
public interface GestorRepository extends JpaRepository<Gestor, Long>{

}
