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
import com.ral.model.Examen;
import com.ral.service.IExamenService;

@RestController
@RequestMapping("/examenes") //se recomienda poner en plural
public class ExamenController {
	
	@Autowired
	private IExamenService service;
	
	@GetMapping
	public ResponseEntity<List<Examen>>  listar() throws Exception {
		List<Examen>  lista = service.listar();
		return new ResponseEntity<List<Examen>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Examen>  listarPorId(@PathVariable("id") Integer idExamen) throws Exception{
		Examen obj = service.buscarPorId(idExamen);
		
		if(obj==null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO"+idExamen);
		}
		
		return new ResponseEntity<Examen>(obj, HttpStatus.OK);
	}
	
	@GetMapping("/hateoas/{id}")
	public EntityModel<Examen> listarPorHateoas(@PathVariable("id") Integer idExamen) throws Exception{
		Examen obj = service.buscarPorId(idExamen);
		
		if(obj==null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO"+idExamen);
		}
		
		// localhost:8080/Examens/{id}
		EntityModel<Examen> recurso = EntityModel.of(obj);
		
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).listarPorId(idExamen));
		recurso.add(link.withRel("Examen-Recurso"));
		return recurso;
	} 
	
	/*@PostMapping
	public ResponseEntity<Examen> registrar(@Valid @RequestBody Examen Examen) throws Exception{
		Examen obj = service.registrar(Examen);
		return new ResponseEntity<Examen>(obj, HttpStatus.CREATED);
	}*/
	
	//Registrar con el modelo de Richarson
	@PostMapping
	public ResponseEntity<Examen> registrar(@Valid @RequestBody Examen examen) throws Exception{
		Examen obj = service.registrar(examen);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdExamen()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Examen> modificar(@Valid @RequestBody Examen examen) throws Exception{
		Examen obj = service.modificar(examen);
		return new ResponseEntity<Examen>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer idExamen) throws Exception {
		
		Examen obj = service.buscarPorId(idExamen);
		
		if(obj.getIdExamen()==null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO: "+idExamen);
		}
		service.eliminar(idExamen);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
