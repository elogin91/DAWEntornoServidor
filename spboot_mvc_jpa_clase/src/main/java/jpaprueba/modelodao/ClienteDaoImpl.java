package jpaprueba.modelodao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jpaprueba.modelo.entitybean.Cliente;
import jpaprueba.repository.ClienteRepository;

@Repository
public class ClienteDaoImpl implements ClienteDao{
	@Autowired
	private ClienteRepository crepo;
	
	@Override
	public List<Cliente> buscarTodos() {
		return crepo.findAll();
	}

	@Override
	public Cliente buscarUno(int idCliente) {
		return crepo.findById(idCliente).orElse(null);
	}

	@Override
	public Cliente insertarUno(Cliente cliente) {
		try {
			return crepo.save(cliente);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int modificarUno(Cliente cliente) {
		if (buscarUno(cliente.getIdCliente()) != null) {
			crepo.save(cliente);
			return 1;
			
		}else
			return 0;
	}

	@Override
	public int borrarUno(int idCliente) {
		if (buscarUno(idCliente) != null) {
			crepo.deleteById(idCliente);
			return 1;
			
		}else
			return 0;
	}

	@Override
	public List<Cliente> buscarPorFacturacionMayorQue(double facturacionAnual) {
		return crepo.findFacturMayor(facturacionAnual);
	}

}
