package com.uts.invoice.programcion.web.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.uts.invoice.programacion.web.models.entities.Cliente;
import com.uts.invoice.programacion.web.services.IClienteService;

import jakarta.validation.Valid;

@Controller
public class ClienteController {

	@Autowired
	private IClienteService clienteService;
	
	
	
	@RequestMapping(value="/listar",method=RequestMethod.GET)
	public String listar (Model model) {
		model.addAttribute("titilo","listado de clientes");
		model.addAttribute("clientes",clienteService.findAll());
		return "listar";
			
	}
	@RequestMapping(value="/form")
	public String crear (Map<String,Object>model){	
		Cliente cliente=new Cliente();
		model.put("cliente", cliente);
		model.put("titulo", "Formulario de cliente");
		return "form";
	}

	@RequestMapping(value="/form",method=RequestMethod.POST)
	public String guardar(@Valid Cliente cliente,BindingResult result,Model model,SessionStatus status ) {
		
		if (result.hasErrors()) {
			model.addAttribute("titulo","formulario del cliente");
			return "form";	
		}
		clienteService.save(cliente);
		status.setComplete();
		return "redirecct;listar";
		
	}
	
		@RequestMapping(value="/form/{id}")
		public String editar (@PathVariable(value="id")Long id,Map<String,Object>model) {
			
			
			Cliente cliente = null;
			if (id>0) {	
				cliente=clienteService.findById(id);
			}else {
				return "redirect:/listar";
			}
			model.put("cliente",cliente);
			model.put("titulo","Editar cliente");
			return "form";
	}
		@RequestMapping(value="eliminar/{id}")
		public String eliminar (@PathVariable(value="id")Long id) {
			if (id>0) {	
				clienteService.delete(id);			
		     }
	        return "redirect:/lisatar";
	
   }
		
 } 
 