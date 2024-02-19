package unir.familias.modelo.dao;

import java.util.List;

import unir.familias.modelo.entity.Familia;

public interface FamiliaDao {
	
	Familia buscarUna(int idFamilia);
	List<Familia> todas();
	

}
