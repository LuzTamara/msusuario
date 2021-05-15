package com.brikton.labapps.msusuario.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.brikton.labapps.msusuario.domain.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente, Integer>{
	
	List<Cliente> findByFechaBajaIsNull();
	
	Optional<Cliente> findByCuit(String cuit);
	
	Optional<Cliente> findByRazonSocial(String razonSocial);
	
	

}
