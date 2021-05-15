package com.brikton.labapps.msusuario.servicioImplementacion;

import java.util.List;
import java.util.Optional;

import com.brikton.labapps.msusuario.domain.Cliente;
import com.brikton.labapps.msusuario.domain.Obra;
import com.brikton.labapps.msusuario.domain.TipoObra;
import com.brikton.labapps.msusuario.repositorios.ObraRepositorio;
import com.brikton.labapps.msusuario.servicioInterfaz.ClienteServicio;
import com.brikton.labapps.msusuario.servicioInterfaz.ObraServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObraServicioImplementacion implements ObraServicio {
	
	ClienteServicio clienteServicio;
	
	@Autowired
	ObraRepositorio repositorio;
	
	public List<Obra> listarObras(TipoObra tipoObraId) {
		if(tipoObraId == null) {
			return this.repositorio.findAll();
		} else {
			return this.repositorio.findAllByTipoObra(tipoObraId);
		}
	}
		
	
	public Optional<Obra> buscarObraPorId(Integer id) throws Exception {
		Optional<Obra> o = this.repositorio.findById(id);
		if(o.isEmpty())
			throw new Exception();
		return o;
	}
	
	public Obra guardarObra(Obra o, Integer clienteId) throws Exception {
		Optional<Cliente> clienteBuscado = this.clienteServicio.buscarClientePorId(clienteId);
		if(clienteBuscado.isPresent()) {
			if(o.getId() != null && this.buscarObraPorId(o.getId()).isPresent() && clienteId == null) {
				this.repositorio.save(o);
			} else {
					clienteBuscado.get().agregarObra(o);
					o.setCliente(clienteBuscado.get());
					this.repositorio.save(o);
					this.clienteServicio.guardarCliente(clienteBuscado.get());
			} 
			return o;
		} else {
			throw new Exception();
		}
	}

	public List<Obra> listarObrasPorCliente(Integer clienteId) throws Exception {
		Optional<Cliente> cliente = this.clienteServicio.buscarClientePorId(clienteId);
		if(!cliente.isPresent()) {
			throw new Exception();
		}
		return this.repositorio.findAllByCliente(cliente.get());
	}
	
	public Double buscarSaldoClienteDeObra(Integer obraId) throws Exception{
		Optional<Obra> o = this.repositorio.findById(obraId);
		if(!o.isPresent()) {
			throw new Exception();
		}
		return o.get().getCliente().getMaxCuentaCorriente();
	}
}
