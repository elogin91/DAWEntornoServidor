package es.fp.restfull.fabrica.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import es.fp.restfull.fabrica.modelo.entitybeans.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer>{
	
	@Query("select p from Producto p where p.familia.idFamilia = ?1")
	
	public List<Producto> buscarPorFamilia(int idFamilia);

}
