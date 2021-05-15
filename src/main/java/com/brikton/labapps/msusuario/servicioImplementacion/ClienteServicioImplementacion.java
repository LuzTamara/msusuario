package com.brikton.labapps.msusuario.servicioImplementacion;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brikton.labapps.msusuario.domain.Cliente;
import com.brikton.labapps.msusuario.domain.Usuario;
import com.brikton.labapps.msusuario.repositorios.ClienteRepositorio;
import com.brikton.labapps.msusuario.servicioInterfaz.ClienteServicio;

@Service
public class ClienteServicioImplementacion implements ClienteServicio {
	
	@Autowired
	ClienteRepositorio repositorio;
	
	@Override
	public List<Cliente> listarClientes() {
		return this.repositorio.findByFechaBajaIsNull();
	}
	
	@Override
	public Optional<Cliente> buscarClientePorId(Integer id) throws Exception {
		Optional<Cliente> c = this.repositorio.findById(id);
		if(c.isEmpty() || c.get().getFechaBaja() != null)
			throw new Exception();
		return c;
	}
	
	@Override
public Optional<Cliente> buscarClientePorCuit(String cuit) throws Exception {
		Optional<Cliente> c = this.repositorio.findByCuit(cuit);
		if(c.isEmpty() || c.get().getFechaBaja() != null)
			throw new Exception();
		return c;
	}

	@Override
public Optional<Cliente> buscarClientePorRazonSocial(String razonSocial) throws Exception {
	Optional<Cliente> c = this.repositorio.findByRazonSocial(razonSocial);
	if(c.isEmpty() || c.get().getFechaBaja() != null)
		throw new Exception();
	return c;
}

	@Override
	@Transactional
public Cliente guardarCliente(Cliente c) throws Exception {
	if(c.getId() != null && this.buscarClientePorId(c.getId()).isPresent()) {
		this.repositorio.save(c);
	} else {
		if(c.getObras().isEmpty()) {
			throw new Exception();
		}
		Usuario u = new Usuario();
		u.setUser(c.getMail());
		u.setPassword("1234");
		this.repositorio.save(c);
		}
	return c;
	}

	@Override
	@Transactional
public void bajaCliente(Integer id) throws Exception {
	// TODO: validar que no haya realizado pedidos
	Optional<Cliente> c = this.buscarClientePorId(id);
	if(!c.isPresent()) {
		throw new Exception();
	}
	c.get().setFechaBaja(new Date());
	this.repositorio.save(c.get());
}
}

