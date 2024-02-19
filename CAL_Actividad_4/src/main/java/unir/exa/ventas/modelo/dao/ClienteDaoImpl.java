package unir.exa.ventas.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import unir.exa.ventas.modelo.entity.Cliente;
import unir.exa.ventas.repository.ClienteRepository;

@Repository
public class ClienteDaoImpl implements ClienteDao{
	@Autowired
	ClienteRepository clirepo;

	@Override
	public int altaCliente(Cliente cliente) {
		try {
			clirepo.save(cliente);
			return 1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	@Override
	public int eliminarCliente(int idCliente) {
		try {
			clirepo.deleteById(idCliente);
			return 1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	@Override
	public List<Cliente> listarClientes() {
		return clirepo.findAll();
	}

	@Override
	public Cliente buscarUnCliente(int idCliente) {
		return clirepo.findById(idCliente).orElse(null);
	}

}
