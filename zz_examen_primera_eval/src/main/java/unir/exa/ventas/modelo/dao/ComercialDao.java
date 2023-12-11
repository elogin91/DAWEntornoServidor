package unir.exa.ventas.modelo.dao;

import java.util.List;

import unir.exa.ventas.modelo.entity.Comercial;

public interface ComercialDao {
	Comercial altaComercial (Comercial comercial); 
	List <Comercial> listarTodos();
	Comercial buscarUnComercial(int idComercial);
	int eliminarComercial(int idComercial);
}
