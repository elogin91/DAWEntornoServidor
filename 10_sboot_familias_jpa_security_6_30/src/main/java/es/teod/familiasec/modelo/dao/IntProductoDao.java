package es.teod.familiasec.modelo.dao;

import java.util.List;

import es.teod.familiasec.modelo.beans.Producto;


public interface IntProductoDao {
	
	int insertarProducto(Producto producto);
	Producto buscarUno(int codigo);
	List<Producto> buscarTodos();
	List<Producto> buscarporFamilia(int codigoFamilia);
	List<Producto> buscarPormarcayColor(String marca, String color);
	int borrarProducto(int codigo);
	int modificarProducto(Producto producto);
	
	

}
