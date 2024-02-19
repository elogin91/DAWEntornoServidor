package jpaprueba.modelodao;

import java.util.List;

import jpaprueba.modelo.entitybean.Cliente;

public interface ClienteDao {
	List <Cliente> buscarTodos();
	Cliente buscarUno (int idCliente);
	Cliente insertarUno (Cliente cliente);
	int  modificarUno(Cliente cliente);
	int borrarUno (int idCliente);
	List <Cliente> buscarPorFacturacionMayorQue(double facturacionAnual);
}
