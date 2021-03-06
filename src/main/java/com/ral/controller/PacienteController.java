package com.ral.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import com.ral.model.Paciente;
import com.ral.service.IPacienteService;

@RestController
@RequestMapping("/pacientes") //se recomienda poner en plural
public class PacienteController {
	
	@Autowired
	private IPacienteService service;
	
	@GetMapping
	public ResponseEntity<List<Paciente>>  listar() throws Exception {
		List<Paciente>  lista = service.listar();
		return new ResponseEntity<List<Paciente>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Paciente>  listarPorId(@PathVariable("id") Integer idPaciente) throws Exception{
		Paciente obj = service.buscarPorId(idPaciente);
		
		if(obj==null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO"+idPaciente);
		}
		
		return new ResponseEntity<Paciente>(obj, HttpStatus.OK);
	}
	
	@GetMapping("/hateoas/{id}")
	public EntityModel<Paciente> listarPorHateoas(@PathVariable("id") Integer idPaciente) throws Exception{
		Paciente obj = service.buscarPorId(idPaciente);
		
		if(obj==null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO"+idPaciente);
		}
		
		// localhost:8080/pacientes/{id}
		EntityModel<Paciente> recurso = EntityModel.of(obj);
		
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).listarPorId(idPaciente));
		recurso.add(link.withRel("Paciente-Recurso"));
		return recurso;
	} 
	
	/*@PostMapping
	public ResponseEntity<Paciente> registrar(@Valid @RequestBody Paciente paciente) throws Exception{
		Paciente obj = service.registrar(paciente);
		return new ResponseEntity<Paciente>(obj, HttpStatus.CREATED);
	}*/
	
	//Registrar con el modelo de Richarson
	@PostMapping
	public ResponseEntity<Paciente> registrar(@Valid @RequestBody Paciente paciente) throws Exception{
		Paciente obj = service.registrar(paciente);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdPaciente()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Paciente> modificar(@Valid @RequestBody Paciente paciente) throws Exception{
		Paciente obj = service.modificar(paciente);
		return new ResponseEntity<Paciente>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer idPaciente) throws Exception {
		
		Paciente obj = service.buscarPorId(idPaciente);
		
		if(obj.getIdPaciente()==null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO: "+idPaciente);
		}
		service.eliminar(idPaciente);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/pageable")
	public ResponseEntity<Page<Paciente>> listarPageable(Pageable pageable) throws Exception{
		Page<Paciente> pacientes = service.listarPageable(pageable);
		return new ResponseEntity<Page<Paciente>>(pacientes, HttpStatus.OK);
	}

}