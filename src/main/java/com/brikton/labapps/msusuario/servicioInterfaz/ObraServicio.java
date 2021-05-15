package com.brikton.labapps.msusuario.servicioInterfaz;

import java.util.List;
import java.util.Optional;

import com.brikton.labapps.msusuario.domain.Obra;

public interface ObraServicio {
	
	List<Obra> listarObras(Integer tipoObraId);
	
	Optional<Obra> buscarObraPorId(Integer id) throws Exception;
	
	Obra guardarObra(Obra obra, Integer clienteId) throws Exception;
	
	List<Obra> listarObrasPorCliente(Integer clienteId) throws Exception;
	
	Double buscarSaldoClienteDeObra(Integer obraId) throws Exception;

}
