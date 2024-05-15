package br.org.serratec.candidatos.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.candidatos.model.Candidato;

public interface CandidatoRepositorio extends JpaRepository<Candidato,Long>{
	
	List <Candidato> findByNumeroCandidatoContaining(String numeroCandidato);
	List <Candidato> findByCidadeContainingIgnoreCase(String cidade);
	List <Candidato> findByCargoContainingIgnoreCase(String cargo);
	List <Candidato> findByCidadeAndCargo(String cidade, String cargo);
}
