package br.org.serratec.candidatos.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.candidatos.model.Candidato;

public interface CandidatoRepositorio extends JpaRepository<Candidato,Long>{

}
