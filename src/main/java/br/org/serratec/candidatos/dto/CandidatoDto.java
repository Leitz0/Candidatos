package br.org.serratec.candidatos.dto;

import br.org.serratec.candidatos.model.Candidato;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


public record CandidatoDto( 
		Long id,
		@NotBlank(message = "Nome do candidato não pode ficar em branco, Favor informar o nome do Candidato")		
		String nome,
		
		@NotBlank (message = "O número do partido não pode ser nulo")
		@Pattern(regexp = "\\d{2,5}", message = "Numero do candidato deve conter de 2 a 5 digitos á depender do cargo")
		String numeroCandidato,
		@NotBlank (message = "O Partido do candidato não pode ficar em branco, Favor informar o partido do Candidato")
		String partido,
		@NotBlank (message = "A cidade do candidato não pode ficar em branco, Favor informar a cidade do Candidato")
		String cidade,
		@NotBlank (message = "O estado do candidato não pode ficar em branco, Favor informar o estado do Candidato")
		String estado,
		@NotBlank (message = "O cargo do candidato não pode ficar em branco, Favor informar o cargo que o Candidato"
				+ "está concorrendo")
		String cargo) {
	
	public Candidato toEntity() {
		return new Candidato (this.id, this.nome, this.numeroCandidato, this.partido, this.cidade, this.estado, this.cargo);
	}
	
}
