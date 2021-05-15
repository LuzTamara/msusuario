package com.brikton.labapps.msusuario.repositorios;

import java.util.List;

import com.brikton.labapps.msusuario.domain.Cliente;
import com.brikton.labapps.msusuario.domain.Obra;
import com.brikton.labapps.msusuario.domain.TipoObra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ObraRepositorio extends JpaRepository<Obra, Integer>{
	
	List<Obra> findAllByCliente(Cliente cliente);

	// @Query("SELECT o from Obra o JOIN TipoObra to "
	// 		+ "WHERE to.tipoObra = :tipoObra")
	@Query("SELECT o FROM Obra o WHERE o.tipo = :tipoObra")
	List<Obra> findAllByTipoObra(@Param("tipoObra") TipoObra tipoObra); 
}
