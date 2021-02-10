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
import com.ral.model.Especialidad;
import com.ral.service.IEspecialidadService;

@RestController
@RequestMapping("/especialidades") //se recomienda poner en plural
public class EspecialidadController {
	
	@Autowired
	private IEspecialidadService service;
	
	@GetMapping
	public ResponseEntity<List<Especialidad>>  listar() throws Exception {
		List<Especialidad>  lista = service.listar();
		return new ResponseEntity<List<Especialidad>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Especialidad>  listarPorId(@PathVariable("id") Integer idEspecialidad) throws Exception{
		Especialidad obj = service.buscarPorId(idEspecialidad);
		
		if(obj==null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO"+idEspecialidad);
		}
		
		return new ResponseEntity<Especialidad>(obj, HttpStatus.OK);
	}
	
	@GetMapping("/hateoas/{id}")
	public EntityModel<Especialidad> listarPorHateoas(@PathVariable("id") Integer idEspecialidad) throws Exception{
		Especialidad obj = service.buscarPorId(idEspecialidad);
		
		if(obj==null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO"+idEspecialidad);
		}
		
		// localhost:8080/especialidads/{id}
		EntityModel<Especialidad> recurso = EntityModel.of(obj);
		
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).listarPorId(idEspecialidad));
		recurso.add(link.withRel("Especialidad-Recurso"));
		return recurso;
	} 
	
	/*@PostMapping
	public ResponseEntity<Especialidad> registrar(@Valid @RequestBody Especialidad especialidad) throws Exception{
		Especialidad obj = service.registrar(especialidad);
		return new ResponseEntity<Especialidad>(obj, HttpStatus.CREATED);
	}*/
	
	//Registrar con el modelo de Richarson
	@PostMapping
	public ResponseEntity<Especialidad> registrar(@Valid @RequestBody Especialidad especialidad) throws Exception{
		Especialidad obj = service.registrar(especialidad);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdEspecialidad()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Especialidad> modificar(@Valid @RequestBody Especialidad especialidad) throws Exception{
		Especialidad obj = service.modificar(especialidad);
		return new ResponseEntity<Especialidad>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer idEspecialidad) throws Exception {
		
		Especialidad obj = service.buscarPorId(idEspecialidad);
		
		if(obj.getIdEspecialidad()==null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO: "+idEspecialidad);
		}
		service.eliminar(idEspecialidad);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
