package com.brikton.labapps.msusuario.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brikton.labapps.msusuario.domain.Obra;
import com.brikton.labapps.msusuario.domain.TipoObra;
import com.brikton.labapps.msusuario.servicioInterfaz.ObraServicio;

@RestController
@RequestMapping("/api/obra")
public class ObraRest {
	
	@Autowired
	ObraServicio obraServicio;
	
	@GetMapping(path="/obra/{id}")
    public ResponseEntity<?> obraPorId(@PathVariable Integer id) {
        
		Optional<Obra> obra = null;
		try {
			obra = this.obraServicio.buscarObraPorId(id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		return ResponseEntity.of(obra);
    }
	
	@GetMapping
	public ResponseEntity<List<Obra>> todos() {
		 return ResponseEntity.ok(this.obraServicio.listarObras(null));
	}
	
	@GetMapping(path="/tipoObra/{tipoObraId}")
	public ResponseEntity<List<Obra>> todosFiltrado(@PathVariable String tipoObraId) {
		 return ResponseEntity.ok(this.obraServicio.listarObras(TipoObra.valueOf(tipoObraId)));
	}
	 
	 @PostMapping
	    public ResponseEntity<?> crear(@RequestBody Obra nueva,
	    								@PathVariable Integer clienteId){
		 Obra creada = null;
			try {
				creada= this.obraServicio.guardarObra(nueva, clienteId);
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			}
			return ResponseEntity.ok(creada);
	  }
	 
	  @PutMapping(path = "/{id}")
	   public ResponseEntity<?> actualizar(@RequestBody Obra nueva, 
	    		 @PathVariable Integer id){
		  Obra actualizada = null;
		  try {
			actualizada = this.obraServicio.guardarObra(nueva, null);
		  } catch (Exception e) {
			  return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			}
			return ResponseEntity.ok(actualizada);
	    }
	    
	    @GetMapping(path = "/obrasPorCliente/{clienteId}")
	    public ResponseEntity<?> obrasPorCliente(@PathVariable Integer clienteId) {
	    	List<Obra> obras = null;
	    	try {
	    		obras = this.obraServicio.listarObrasPorCliente(clienteId);
	    	} catch (Exception e) {
	    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    	}
	    	return ResponseEntity.ok(obras);
	    }
	    
	    @GetMapping(path = "/saldo/{obraId}")
	    public ResponseEntity<?> saldoClienteDeObra(@PathVariable Integer obraId) {
	    	Double saldo = null;
	    	try {
	    		saldo = this.obraServicio.buscarSaldoClienteDeObra(obraId);
	    	} catch (Exception e) {
	    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    	}
	    	return ResponseEntity.ok(saldo);
	    }
	}
