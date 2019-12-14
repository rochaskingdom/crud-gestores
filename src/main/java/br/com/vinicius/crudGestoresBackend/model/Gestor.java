package br.com.vinicius.crudGestoresBackend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gestores")
public class Gestor {

	private long id;
	private String nome;
	private String matricula;
	private String nascimento;
	private String setor;

	public Gestor() {

	}

	public Gestor(String nome, String matricula, String nascimento, String setor) {
		this.nome = nome;
		this.matricula = matricula;
		this.nascimento = nascimento;
		this.setor = setor;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "nome", nullable = false)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "matricula", nullable = false)
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	@Column(name = "nascimento", nullable = false)
	public String getNascimento() {
		return nascimento;
	}

	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}

	@Column(name = "setor", nullable = false)
	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	@Override
	public String toString() {
		return "Gestor [id=" + id + ", nome=" + nome + ", matricula=" + matricula + ", nascimento=" + nascimento
				+ ", setor=" + setor + "]";
	}

}
