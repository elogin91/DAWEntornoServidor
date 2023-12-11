package unir.exa.ventas.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import unir.exa.ventas.modelo.entity.Pedido;

import unir.exa.ventas.repository.PedidoRepository;

@Repository
public class PedidoDaoImpl implements PedidoDao{
	@Autowired
	PedidoRepository pedrepo;
	
	@Override
	public Pedido altaPedido(Pedido pedido) {
		try {
			return pedrepo.save(pedido);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public int eliminarPedido(int idPedido) {
		try {
			pedrepo.deleteById(idPedido);
			return 1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	@Override
	public List<Pedido> listarTodoPedidos() {
		return pedrepo.findAll();
	}

	@Override
	public Pedido buscarUnPedido(int idPedido) {
		return pedrepo.findById(idPedido).orElse(null);
	}

	@Override
	public List<Pedido> pedidosByCliente(int idCliente) {
		
		return pedrepo.pedidosByIdCliente(idCliente);
	}

	@Override
	public List<Pedido> pedidosByComercial(int idComercial) {
		return pedrepo.pedidosByIdComercial(idComercial);
	}
	
}
