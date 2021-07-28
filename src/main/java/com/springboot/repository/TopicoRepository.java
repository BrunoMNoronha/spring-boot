package com.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.model.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

	List<Topico> findByCursoNome(String nomeCurso);

//	@Query("SELECT t FROM topico t WHERE t.curso.nome = :nomeCurso")
//	List<Topico> findByNomeDoCurso(@Param("nomeCurso") String nomeCurso);

}
