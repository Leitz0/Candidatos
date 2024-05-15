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
	
	public Optional<CandidatoDto> obterPorId(Long id) {
		Optional<Candidato> candidato = repository.findById(id);
		if (candidato.isPresent()) {
			return Optional.of(candidato.get().toDto());
		}
		return Optional.empty();
	}
	
	public CandidatoDto incluirCandidato(CandidatoDto candidato){
		 Candidato candidatoEntity = repository.save(candidato.toEntity());
	        return candidatoEntity.toDto();
		
	}
	
	public Optional<CandidatoDto> atualizarCandidato(Long id, CandidatoDto candidato) {
		Candidato candidatoEntity = candidato.toEntity();
		
		if(repository.existsById(id)) {
			candidatoEntity.setId(id);
			repository.save(candidatoEntity);
			return Optional.of(candidatoEntity.toDto());
		}
		return Optional.empty();
	}
	
	public boolean excluirCandidato(Long id) {
		if(!repository.existsById(id)) {
			return false;
		}
		
		repository.deleteById(id);
		return true;
	}

	public List<CandidatoDto> obterPorNumero(String numeroCandidato) {
		
		return repository.findByNumeroCandidatoContaining(numeroCandidato).stream().map(c-> new CandidatoDto(c.getId(), c.getNome(), c.getNumeroCandidato(),
				c.getPartido(), c.getCidade(), c.getEstado(), c.getCargo())).toList();
	}

	public List<CandidatoDto> obterPorCidade(String cidade) {
		return repository.findByCidadeContainingIgnoreCase(cidade).stream().map(c-> new CandidatoDto(c.getId(), c.getNome(), c.getNumeroCandidato(),
				c.getPartido(), c.getCidade(), c.getEstado(), c.getCargo())).toList();
	}

	public List<CandidatoDto> obterPorCargo(String cargo) {
		return repository.findByCargoContainingIgnoreCase(cargo).stream().map(c-> new CandidatoDto(c.getId(), c.getNome(), c.getNumeroCandidato(),
				c.getPartido(), c.getCidade(), c.getEstado(), c.getCargo())).toList();
	}

	public List<CandidatoDto>  obterPorCidadeCargo(String cidade, String cargo) {
		return repository.findByCidadeAndCargo(cidade,cargo).stream().map(c-> new CandidatoDto(c.getId(), c.getNome(), c.getNumeroCandidato(),
				c.getPartido(), c.getCidade(), c.getEstado(), c.getCargo())).toList();
	}

	public List<CandidatoDto> obterPorCidadePartido(String partido, String cidade) {
		return repository.findByPartidoAndCidade(partido,cidade).stream().map(c-> new CandidatoDto(c.getId(), c.getNome(), c.getNumeroCandidato(),
				c.getPartido(), c.getCidade(), c.getEstado(), c.getCargo())).toList();
	}
	
	
	
	
	
	
	
	
	
	
	
}