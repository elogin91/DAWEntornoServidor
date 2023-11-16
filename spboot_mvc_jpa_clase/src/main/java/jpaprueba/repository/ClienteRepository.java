package jpaprueba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jpaprueba.modelo.entitybean.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	@Query("select c from Cliente c where c.facturacionAnual > ?1")
	public List<Cliente> findFacturMayor(double facturacionAnual);
}
