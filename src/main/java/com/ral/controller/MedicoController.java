package com.ral.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
//Importaciones necesarias para el Hateoas
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ral.exception.ModeloNotFoundException;
import com.ral.model.Medico;
import com.ral.service.IMedicoService;

@RestController
@RequestMapping("/medicos") //se recomienda poner en plural
public class MedicoController {
	
	@Autowired
	private IMedicoService service;
	
	@GetMapping
	public ResponseEntity<List<Medico>>  listar() throws Exception {
		List<Medico>  lista = service.listar();
		return new ResponseEntity<List<Medico>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Medico>  listarPorId(@PathVariable("id") Integer idMedico) throws Exception{
		Medico obj = service.buscarPorId(idMedico);
		
		if(obj==null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO"+idMedico);
		}
		
		return new ResponseEntity<Medico>(obj, HttpStatus.OK);
	}
	
	@GetMapping("/hateoas/{id}")
	public EntityModel<Medico> listarPorHateoas(@PathVariable("id") Integer idMedico) throws Exception{
		Medico obj = service.buscarPorId(idMedico);
		
		if(obj==null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO"+idMedico);
		}
		
		// localhost:8080/Medicos/{id}
		EntityModel<Medico> recurso = EntityModel.of(obj);
		
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).listarPorId(idMedico));
		recurso.add(link.withRel("Medico-Recurso"));
		return recurso;
	} 
	
	/*@PostMapping
	public ResponseEntity<Medico> registrar(@Valid @RequestBody Medico medico) throws Exception{
		Medico obj = service.registrar(Medico);
		return new ResponseEntity<Medico>(obj, HttpStatus.CREATED);
	}*/
	
	//Registrar con el modelo de Richarson
	@PostMapping
	public ResponseEntity<Medico> registrar(@Valid @RequestBody Medico medico) throws Exception{
		Medico obj = service.registrar(medico);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdMedico()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Medico> modificar(@Valid @RequestBody Medico medico) throws Exception{
		Medico obj = service.modificar(medico);
		return new ResponseEntity<Medico>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer idMedico) throws Exception {
		
		Medico obj = service.buscarPorId(idMedico);
		
		if(obj.getIdMedico()==null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO: "+idMedico);
		}
		service.eliminar(idMedico);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
