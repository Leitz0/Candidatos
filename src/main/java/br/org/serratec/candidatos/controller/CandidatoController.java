package br.org.serratec.candidatos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.candidatos.dto.CandidatoDto;
import br.org.serratec.candidatos.dto.CargoCidadeDto;
import br.org.serratec.candidatos.dto.CargoCidadeDto;
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
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		if(!service.excluirCandidato(id)){
		return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/numero")
	public ResponseEntity<List<CandidatoDto>> obterCandidatoPorNumero(@RequestBody String numeroCandidato){
		return ResponseEntity.ok(service.obterPorNumero(numeroCandidato));
	}
	
	@GetMapping("/cidade")
	public ResponseEntity<List<CandidatoDto>> obterCandidatoPorCidade(@RequestBody String cidade){
		return ResponseEntity.ok(service.obterPorCidade(cidade));
	}
	
	@GetMapping("/cargo")
	public ResponseEntity<List<CandidatoDto>> obterCandidatoPorCargo(@RequestBody String cargo){
		return ResponseEntity.ok(service.obterPorCargo(cargo));
	}
	@GetMapping("/cidadecargo")
	public ResponseEntity<List<CandidatoDto>> obterCandidatoPorCidadeCargo(@RequestBody CargoCidadeDto dto ){
		return ResponseEntity.ok(service.obterPorCidadeCargo(dto.cidade(),dto.cargo()));
	}
	
	@GetMapping("/partidocidade")
	public ResponseEntity<List<CandidatoDto>> obterCandidatoPorCidadePardido(@RequestBody PartidoDto dto){
		return ResponseEntity.ok(service.obterPorCidadePartido(dto.partido(), dto.cidade()));
	}
}
