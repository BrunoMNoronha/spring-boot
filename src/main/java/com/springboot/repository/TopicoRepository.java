package com.springboot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.model.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

	Page<Topico> findByCursoNome(String nomeCurso, Pageable pageable);

//	@Query("SELECT t FROM topico t WHERE t.curso.nome = :nomeCurso")
//	List<Topico> findByNomeDoCurso(@Param("nomeCurso") String nomeCurso);

}
