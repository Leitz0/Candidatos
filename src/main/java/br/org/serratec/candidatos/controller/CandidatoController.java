package br.org.serratec.candidatos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.candidatos.dto.CandidatoDto;
import br.org.serratec.candidatos.service.CandidatoService;
import jakarta.validation.Valid;
@RestController
@RequestMapping("/candidatos")
public class CandidatoController {
	
	@Autowired
	private CandidatoService service;
	
	@GetMapping
	public ResponseEntity <List<CandidatoDto>> buscarTodos() {
		return ResponseEntity.ok(service.obterTodos());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CandidatoDto cadastrarCandidato(@Valid @RequestBody CandidatoDto candidato){
		return service.incluirCandidato(candidato);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CandidatoDto> localizarPorId(@PathVariable Long id){
		Optional<CandidatoDto> candidato = service.obterPorId(id);
		
		if(candidato.isPresent()) {
			return ResponseEntity.ok(candidato.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CandidatoDto> alterarCandidato(@PathVariable Long id, @RequestBody CandidatoDto candidatoAlt){
		Optional <CandidatoDto> candidato = service.atualizarCandidato(id, candidatoAlt);
		
		if(candidato.isPresent()) {
			return ResponseEntity.ok(candidato.get());
		}
		return ResponseEntity.notFound().build();
	}
	
}
