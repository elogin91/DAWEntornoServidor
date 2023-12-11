package unir.exa.ventas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import unir.exa.ventas.modelo.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
	@Query("select p from Pedido p where p.cliente.idCliente=?1")
	public List<Pedido> pedidosByIdCliente(int idCliente);
	
	@Query("select p from Pedido p where p.comercial.idComercial=?1")
	public List<Pedido> pedidosByIdComercial(int idComercial);
}
