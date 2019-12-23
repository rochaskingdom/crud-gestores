package br.com.vinicius.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vinicius.forum.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

	Curso findByNome(String nome);

}
