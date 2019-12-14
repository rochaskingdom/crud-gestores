package br.com.vinicius.crudGestoresBackend.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vinicius.crudGestoresBackend.exception.ResourceNotFoundException;
import br.com.vinicius.crudGestoresBackend.model.Gestor;
import br.com.vinicius.crudGestoresBackend.repository.GestorRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class GestorController {
	@Autowired
	private GestorRepository gestorRepository;

	@GetMapping("/gestores")
	public List<Gestor> getAllGestores() {
		return gestorRepository.findAll();
	}

	@GetMapping("/gestores/{id}")
	public ResponseEntity<Gestor> getGestorById(@PathVariable(value = "id") Long gestorId)
			throws ResourceNotFoundException {
		Gestor gestor = gestorRepository.findById(gestorId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + gestorId));
		return ResponseEntity.ok().body(gestor);
	}

	@PostMapping("/gestores")
	public Gestor createEmployee(@Valid @RequestBody Gestor gestor) {
		return gestorRepository.save(gestor);
	}

	@PutMapping("/gestores/{id}")
	public ResponseEntity<Gestor> updateEmployee(@PathVariable(value = "id") Long gestorId,
			@Valid @RequestBody Gestor gestorDetails) throws ResourceNotFoundException {
		Gestor gestor = gestorRepository.findById(gestorId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + gestorId));

		gestor.setNome(gestorDetails.getNome());
		gestor.setMatricula(gestorDetails.getMatricula());
		gestor.setNascimento(gestorDetails.getNascimento());
		gestor.setSetor(gestorDetails.getSetor());
		final Gestor updatedGestor = gestorRepository.save(gestor);
		return ResponseEntity.ok(updatedGestor);
	}

	@DeleteMapping("/gestores/{id}")
	public Map<String, Boolean> deleteGestor(@PathVariable(value = "id") Long gestorId)
			throws ResourceNotFoundException {
		Gestor gestor = gestorRepository.findById(gestorId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + gestorId));

		gestorRepository.delete(gestor);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
