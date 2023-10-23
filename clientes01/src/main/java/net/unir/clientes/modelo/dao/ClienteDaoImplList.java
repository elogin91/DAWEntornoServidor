package net.unir.clientes.modelo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import net.unir.clientes.modelo.javabean.Cliente;


@Repository
public class ClienteDaoImplList implements ClienteDao {
	
	private List<Cliente> lista;
	private static int idAuto;
	
	static {
		idAuto=0;
	}
	
	public ClienteDaoImplList() {
		lista = new ArrayList<>();
		cargarLista();
	}
	
	private void cargarLista() {
		lista.add(new Cliente(1, "UNIRFP", 128, 1_000_000));
		lista.add(new Cliente(2, "UNIR", 1000, 7_000_000));
		lista.add(new Cliente(3, "TTT", 67, 3_000_000));
		idAuto = 3;
		
	}

	@Override
	public Cliente findById(int idCliente) {
		for (int i = 0; i < lista.size();i++) {
			if (lista.get(i).getIdCliente() == idCliente)
				return lista.get(i);
		}
		return null;
	}

	@Override
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return lista;
	}

	@Override
	public int insert(Cliente cliente) {
		if (!lista.contains(cliente)) {
			cliente.setIdCliente(++idAuto);
			lista.add(cliente);
			
			return 1;
		}
		return 0;
	}

	@Override
	public int delete(int idCliente) {
		Cliente cliente = findById(idCliente);
		if (cliente == null) 
			return 0;
		
		return lista.remove(cliente) ? 1 : 0;
	}

	@Override
	public int updateOne(Cliente cliente) {
		int pos = lista.indexOf(cliente);
		if (pos == -1) {
			return 0;
		}
		lista.set(pos, cliente);
		return 1;
	}

	

}
