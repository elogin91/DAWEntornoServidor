package unir.exa.ventas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import unir.exa.ventas.modelo.entity.Comercial;

public interface ComercialRepository extends JpaRepository<Comercial, Integer>{
	 @Query ("select c from Comercial c where c.idComercial IN (select p.comercial.idComercial from Pedido p where p.cliente.idCliente=?1)")
	 public List<Comercial>buscarComercialByCliente(int idCliente);
	 
	 @Query ("select c from Comercial c where c.idComercial IN (select distinct p.comercial.idComercial from Pedido p)")
	 public List<Comercial>comercialConPedidos();
}
