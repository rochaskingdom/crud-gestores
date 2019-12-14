package br.com.vinicius.crudgestores.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vinicius.crudgestores.models.Gestor;
import br.com.vinicius.crudgestores.repositories.GestorRepository;

@Service
@Transactional
public class GestorService {

	@Autowired
	private GestorRepository repo;

	public List<Gestor> listAll() {
		return repo.findAll();
	}

	public void save(Gestor gestor) {
		repo.save(gestor);
	}

	public Gestor get(long id) {
		return repo.findById(id).get();
	}

	public void delete(long id) {
		repo.deleteById(id);
	}
}
