package unir.exa.ventas.modelo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import unir.exa.ventas.modelo.entity.Pedido;
import unir.exa.ventas.repository.PedidoRepository;

@Repository
public class PedidoDaoImpl implements PedidoDao {
	@Autowired
	PedidoRepository pedidoRepository;

	@Override
	public Pedido altaPedido(Pedido pedido) {
		try {
			return pedidoRepository.save(pedido);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public int eliminarPedido(int idPedido) {
		try {
			if (pedidoRepository.findById(idPedido).isPresent()) {
				pedidoRepository.deleteById(idPedido);
			}
			return 1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	@Override
	public List<Pedido> listarTodoPedidos() {
		return pedidoRepository.findAll();
	}

	@Override
	public Pedido buscarUnPedido(int idPedido) {
		return pedidoRepository.findById(idPedido).orElse(null);
	}

	@Override
	public List<Pedido> pedidosByCliente(int idCliente) {

		return pedidoRepository.pedidosByIdCliente(idCliente);
	}

	@Override
	public List<Pedido> pedidosByComercial(int idComercial) {
		return pedidoRepository.pedidosByIdComercial(idComercial);
	}

}
