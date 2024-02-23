package es.fp.restfull.fabrica.modelo.dao;

import java.util.List;

import es.fp.restfull.fabrica.modelo.entitybeans.Familia;

public interface FamiliaDao {
	
	List<Familia> findAll();
	Familia verUna(int idFamilia);
	int insertar(Familia familia);
	int modificar(Familia familia);
	int eliminar(int idFamilia);
	

}
