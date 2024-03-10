package unir.exa.ventas.modelo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import unir.exa.ventas.modelo.entity.Comercial;
import unir.exa.ventas.repository.ComercialRepository;
import unir.exa.ventas.repository.PedidoRepository;

@Repository
public class ComercialDaoImpl implements ComercialDao {
	@Autowired
	ComercialRepository comercialRepository;
	@Autowired
	PedidoRepository pedidoRepository;

	@Override
	public Comercial altaComercial(Comercial comercial) {
		try {
			if (comercialRepository.findById(comercial.getIdComercial()).isEmpty()) {
				return comercialRepository.save(comercial);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public boolean eliminarComercial(int idComercial) {
		try {
			if (comercialRepository.findById(idComercial).isPresent()&& pedidoRepository.pedidosByIdComercial(idComercial).isEmpty()) {
				comercialRepository.deleteById(idComercial);
				return true;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	@Override
	public List<Comercial> listarTodos() {
		return comercialRepository.findAll();
	}

	@Override
	public Comercial buscarUnComercial(int idComercial) {
		return comercialRepository.findById(idComercial).orElse(null);
	}

	@Override
	public List<Comercial> buscarComercialPorCliente(int idComercial) {
		return comercialRepository.buscarComercialByCliente(idComercial);
	}

	@Override
	public List<Comercial> buscarComercialesConPedidos() {
		return comercialRepository.comercialConPedidos();
	}

}
