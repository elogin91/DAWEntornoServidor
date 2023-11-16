package unir.familias.modelo.dao;

import java.util.List;

import unir.familias.modelo.entity.Producto;

public interface ProductoDao {
	
	Producto buscarUno(int idProducto);
	List<Producto> todos();
	List<Producto> buscarProductosPorFamilia(int idFamilia);
	Producto insertOne(Producto producto);

}
