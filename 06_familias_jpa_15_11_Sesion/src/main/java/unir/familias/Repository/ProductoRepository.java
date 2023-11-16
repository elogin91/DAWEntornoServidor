package unir.familias.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import unir.familias.modelo.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto,	Integer>{
	
	@Query("select p from Producto p where p.familia.idFamilia=?1")
	public List<Producto> findProductosPorFamilia(int idFamilia);

}
