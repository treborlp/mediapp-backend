package com.ral.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ral.dto.ConsultaListaExamenDTO;
import com.ral.dto.ConsultaResumenDTO;
import com.ral.dto.FiltroConsultaDTO;
import com.ral.exception.ModeloNotFoundException;
import com.ral.model.Consulta;
import com.ral.service.IConsultaService;

@RestController
@RequestMapping("/consultas") //se recomienda poner en plural
public class ConsultaController {
	
	@Autowired
	private IConsultaService service;
	
	@GetMapping
	public ResponseEntity<List<Consulta>>  listar() throws Exception {
		List<Consulta>  lista = service.listar();
		return new ResponseEntity<List<Consulta>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Consulta>  listarPorId(@PathVariable("id") Integer idConsulta) throws Exception{
		Consulta obj = service.buscarPorId(idConsulta);
		
		if(obj==null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO"+idConsulta);
		}
		
		return new ResponseEntity<Consulta>(obj, HttpStatus.OK);
	}
	
	@GetMapping("/hateoas/{id}")
	public EntityModel<Consulta> listarPorHateoas(@PathVariable("id") Integer idConsulta) throws Exception{
		Consulta obj = service.buscarPorId(idConsulta);
		
		if(obj==null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO"+idConsulta);
		}
		
		// localhost:8080/Consultas/{id}
		EntityModel<Consulta> recurso = EntityModel.of(obj);
		
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).listarPorId(idConsulta));
		recurso.add(link.withRel("Consulta-Recurso"));
		return recurso;
	} 
	
	/*@PostMapping
	public ResponseEntity<Consulta> registrar(@Valid @RequestBody Consulta Consulta) throws Exception{
		Consulta obj = service.registrar(Consulta);
		return new ResponseEntity<Consulta>(obj, HttpStatus.CREATED);
	}*/
	
	//Registrar con el modelo de Richarson //SIN DTO
	/*@PostMapping
	public ResponseEntity<Consulta> registrar(@Valid @RequestBody Consulta consulta) throws Exception{
		//Consulta obj = service.registrar(consulta);
		Consulta obj = service.registrarTransaccional(consulta); //Con este metodo se registra el detalle de consulta

		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdConsulta()).toUri();
		return ResponseEntity.created(location).build();
	}*/
	
	//CON DTO
	
	@PostMapping
	public ResponseEntity<Consulta> registrar(@Valid @RequestBody ConsultaListaExamenDTO dto) throws Exception{
		//Consulta obj = service.registrar(consulta);
		Consulta obj = service.registrarTransaccionalDTO(dto); //Con este metodo se registra el detalle de consulta

		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdConsulta()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Consulta> modificar(@Valid @RequestBody Consulta consulta) throws Exception{
		Consulta obj = service.modificar(consulta);
		return new ResponseEntity<Consulta>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer idConsulta) throws Exception {
		
		Consulta obj = service.buscarPorId(idConsulta);
		
		if(obj.getIdConsulta()==null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO: "+idConsulta);
		}
		service.eliminar(idConsulta);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("/buscar/otros")
	public ResponseEntity<List<Consulta>> buscarOtro(@RequestBody FiltroConsultaDTO filtro) throws Exception {
		
		List<Consulta> consultas = new ArrayList<>();
		consultas = service.buscar(filtro);
		
		return new ResponseEntity<List<Consulta>>(consultas, HttpStatus.OK);
		
	}
	
	@GetMapping("/buscar")
	public ResponseEntity<List<Consulta>> buscarFecha(@RequestParam("fecha") String fecha) throws Exception {
		
		List<Consulta> consultas = new ArrayList<>();
		consultas = service.buscarFecha(LocalDateTime.parse(fecha));
		
		return new ResponseEntity<List<Consulta>>(consultas, HttpStatus.OK);
		
	}
	
	@GetMapping("/listarresumen")
	public ResponseEntity<List<ConsultaResumenDTO>> listarResumen() throws Exception {
		
		List<ConsultaResumenDTO> consultas = new ArrayList<>();
		consultas = service.listarReumen();
		
		return new ResponseEntity<List<ConsultaResumenDTO>>(consultas, HttpStatus.OK);
		
	}

}
