package com.ral.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ral.model.Paciente;
import com.ral.service.IPacienteService;

@RestController
@RequestMapping("/pacientes") //se recomienda poner en plural
public class PacienteController {
	
	@Autowired
	private IPacienteService service;
	
	@GetMapping
	public List<Paciente> listar() throws Exception {
		return service.listar();
	}
	
	@GetMapping("/{id}")
	public Paciente listarPorId(@PathVariable("id") Integer idPaciente) throws Exception{
		return service.buscarPorId(idPaciente);
	}
	
	@PostMapping
	public Paciente registrar(@Valid @RequestBody Paciente paciente) throws Exception{
		return service.registrar(paciente);
	}
	
	@PutMapping
	public Paciente modificar(@Valid @RequestBody Paciente paciente) throws Exception{
		return service.modificar(paciente);
	}
	
	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable("id") Integer idPaciente) throws Exception {
		service.eliminar(idPaciente);
	}

}
