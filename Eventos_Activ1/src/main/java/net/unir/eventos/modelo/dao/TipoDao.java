package net.unir.eventos.modelo.dao;

import java.util.List;

import net.unir.eventos.modelo.javabean.Tipo;


public interface TipoDao {
	//Método que devuelve una lista de tipos.
	List<Tipo>findAll();
	//Método que te devuelve un tipo según una ID de busqueda
	Tipo findById(int idTipo);
	}
