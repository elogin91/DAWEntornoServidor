package unir.exa.ventas.modelo.dao;

import java.util.List;

import unir.exa.ventas.modelo.entity.Pedido;

public interface PedidoDao {
	Pedido altaPedido (Pedido pedido); 
	Pedido buscarUnPedido(int idPedido);
	List <Pedido> pedidosByCliente(int idCliente);
	List <Pedido> pedidosByComercial(int idComercial);
	int eliminarPedido(int idPedido);
	List<Pedido> listarTodoPedidos();
}