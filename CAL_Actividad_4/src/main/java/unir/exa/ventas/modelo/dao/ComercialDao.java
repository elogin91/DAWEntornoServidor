package unir.exa.ventas.modelo.dao;

import java.util.List;

import unir.exa.ventas.modelo.entity.Comercial;

public interface ComercialDao {
	Comercial altaComercial (Comercial comercial); 
	List <Comercial> listarTodos();
	List <Comercial> buscarComercialesConPedidos();
	List <Comercial> buscarComercialPorCliente(int idComercial);
	Comercial buscarUnComercial(int idComercial);
	boolean eliminarComercial(int idComercial);
}
