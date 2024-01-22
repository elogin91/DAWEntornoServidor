package es.teod.familiasec.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.teod.familiasec.modelo.beans.Producto;
import es.teod.familiasec.modelo.repository.ProductoRepository;


@Service
public class ProductoDaoImplMy8 implements IntProductoDao{
	@Autowired
	ProductoRepository prepo;

	@Override
	public int insertarProducto(Producto producto) {
		int filas =0;
		try {
			prepo.save(producto);
			filas=1;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return filas;
	}

	@Override
	public Producto buscarUno(int codigo) {
		// TODO Auto-generated method stub
		return prepo.findById(codigo).orElse(null);
	}

	@Override
	public List<Producto> buscarTodos() {
		// TODO Auto-generated method stub
		return prepo.findAll();
	}

	@Override
	public List<Producto> buscarporFamilia(int codigoFamilia) {
		// TODO Auto-generated method stub
		return prepo.findPorFamilia(codigoFamilia);
	}

	@Override
	public List<Producto> buscarPormarcayColor(String marca, String color) {
		// TODO Auto-generated method stub
		return prepo.findByColorAndMarca(color, marca);
	}

	@Override
	public int borrarProducto(int codigo) {
		int filas = 0;
		try {
			prepo.deleteById(codigo);
			filas=1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return filas;
	}

	@Override
	public int modificarProducto(Producto producto) {
		int filas = 0;
		Producto p1 = null;
		try {
			p1 = prepo.findById(producto.getCodigo()).orElse(null);
			p1 = producto;
			prepo.save(p1);
			filas=1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return filas;
	}

	

}
