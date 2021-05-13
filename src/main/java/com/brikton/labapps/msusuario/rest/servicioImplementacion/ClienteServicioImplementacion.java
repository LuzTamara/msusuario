package com.brikton.labapps.msusuario.rest.servicioImplementacion;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.brikton.labapps.msusuario.domain.Cliente;
import com.brikton.labapps.msusuario.domain.Usuario;
import com.brikton.labapps.msusuario.servicioInterfaz.ClienteServicio;

public class ClienteServicioImplementacion implements ClienteServicio {
	
	public List<Cliente> listarClientes() {
		//validar que no tenga fechaBaja
		return this.repositorio.buscarTodos();
	}
	
	public Optional<Cliente> buscarClientePorId(Integer id) throws Exception {
		//validar que no tenga fechaBaja
		Optional<Cliente> c = this.repositorio.buscarClientePorId(id);
		if(c.isEmpty())
			throw new Exception();
		return c;
	}
	
public Optional<Cliente> buscarClientePorCuit(String cuit) throws Exception {
	//validar que no tenga fechaBaja
		Optional<Cliente> c = this.repositorio.buscarClientePorCuit(cuit);
		if(c.isEmpty())
			throw new Exception();
		return c;
	}

public Optional<Cliente> buscarClientePorRazonSocial(String razonSocial) throws Exception {
	//validar que no tenga fechaBaja
	Optional<Cliente> c = this.repositorio.buscarClientePorRazonSocial(razonSocial);
	if(c.isEmpty())
		throw new Exception();
	return c;
}

public Cliente guardarCliente(Cliente c) throws Exception {
	if(c.getId() != null && this.buscarClientePorId(c.getId()).isPresent()) {
		this.repositorio.guardarCliente(c);
	} else {
	if(c.getObras().isEmpty()) {
		throw new Exception();
	}
	Usuario u = new Usuario();
	u.setUser(c.getRazonSocial());
	u.setPassword("1234");
	this.repositorio.guardarCliente(c);
	}
	return c;
	}

public void bajaCliente(Integer id) throws Exception {
	//validar que no haya realizado pedidos
	Optional<Cliente> c = this.buscarClientePorId(id);
	if(!c.isPresent()) {
		throw new Exception();
	}
	c.get().setFechaBaja(new Date());
	this.guardarCliente(c.get());
}
}

