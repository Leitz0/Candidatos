package br.org.serratec.candidatos.model;

import br.org.serratec.candidatos.dto.CandidatoDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Candidato {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String numeroCandidato;
	private String partido;
	private String cidade;
	private String estado;
	private String cargo;
	
	
	public Candidato () {}
	
	public Candidato(Long id, String nome, String numeroCandidato, String partido, String cidade, String estado,
			String cargo) {
		super();
		this.id = id;
		this.nome = nome;
		this.numeroCandidato = numeroCandidato;
		this.partido = partido;
		this.cidade = cidade;
		this.estado = estado;
		this.cargo = cargo;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNumeroCandidato() {
		return numeroCandidato;
	}
	public void setNumeroCandidato(String numeroCandidato) {
		this.numeroCandidato = numeroCandidato;
	}
	public String getPartido() {
		return partido;
	}
	public void setPartido(String partido) {
		this.partido = partido;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	

	public CandidatoDto toDto() {
		return new CandidatoDto (this.id, this.nome, this.numeroCandidato, this.partido, this.cidade, this.estado, this.cargo);
	}
	
}
