package br.org.serratec.candidatos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.candidatos.dto.CandidatoDto;
import br.org.serratec.candidatos.model.Candidato;
import br.org.serratec.candidatos.repository.CandidatoRepositorio;

@Service
public class CandidatoService {

	@Autowired
	private CandidatoRepositorio repository;
	
	public List<CandidatoDto> obterTodos() {
		return repository.findAll().stream().map(c-> new CandidatoDto(c.getId(), c.getNome(), c.getNumeroCandidato(),
				c.getPartido(), c.getCidade(), c.getEstado(), c.getCargo())).toList();
	}
	
	public CandidatoDto incluirCandidato(CandidatoDto candidato){
		 Candidato candidatoEntity = repository.save(candidato.toEntity());
	        return candidatoEntity.toDto();
		
	}
	
	public Optional<CandidatoDto> obterPorId(Long id){
		Optional <Candidato> candidato = repository.findById(id);
		if(candidato.isPresent()) {
		return Optional.of(candidato.get().toDto());
		}
		return Optional.empty();
	}
	
	public Optional<CandidatoDto> atualizarCandidato( Long id,  CandidatoDto candidato){
		Candidato candidatoEntity = candidato.toEntity();
		
		if(repository.existsById(id)) {
			candidatoEntity.setId(id);
			repository.save(candidatoEntity);
			return Optional.of(candidatoEntity.toDto());
		}
		return Optional.empty();
	}
	
}