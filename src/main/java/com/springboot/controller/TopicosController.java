package com.springboot.controller;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.springboot.controller.dto.DetailTopicoDTO;
import com.springboot.controller.dto.TopicoDTO;
import com.springboot.controller.form.TopicoFORM;
import com.springboot.controller.form.UpdateTopicoFORM;
import com.springboot.model.Topico;
import com.springboot.repository.CursoRepository;
import com.springboot.repository.TopicoRepository;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

	@Autowired
	private TopicoRepository topicoRepository;

	@Autowired
	private CursoRepository cursoRepository;

	@GetMapping
	@Cacheable(value = "index")
	public Page<TopicoDTO> index(@RequestParam(required = false) String nomeCurso, Pageable pageable) {

		if (nomeCurso == null) {
			Page<Topico> topicos = topicoRepository.findAll(pageable);
			return TopicoDTO.converter(topicos);
		} else {
			Page<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso, pageable);
			return TopicoDTO.converter(topicos);
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<DetailTopicoDTO> show(@PathVariable Long id) {

		try {
			Topico topico = topicoRepository.getById(id);
			return ResponseEntity.ok(new DetailTopicoDTO(topico));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

	}

	@PostMapping
	public ResponseEntity<TopicoDTO> store(@RequestBody @Valid TopicoFORM form, UriComponentsBuilder uriBuilder) {
		Topico topico = form.converter(cursoRepository);
		topicoRepository.save(topico);
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicoDTO(topico));
	}

	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<TopicoDTO> update(@PathVariable Long id, @RequestBody @Valid UpdateTopicoFORM form) {

		try {
			Topico topico = form.update(id, topicoRepository);
			return ResponseEntity.ok(new TopicoDTO(topico));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> destroy(@PathVariable Long id) {

		try {
			topicoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

	}

}
