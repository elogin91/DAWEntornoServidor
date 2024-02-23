package unir.exa.ventas.modelo.service;

import java.util.List;

import unir.exa.ventas.modelo.entity.Cliente;

public interface ClienteDao {
	int altaCliente(Cliente cliente);

	int eliminarCliente(int idCliente);

	Cliente buscarUnCliente(int idCliente);

	List<Cliente> listarClientes();
}
