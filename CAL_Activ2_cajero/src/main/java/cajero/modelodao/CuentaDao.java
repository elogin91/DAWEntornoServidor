package cajero.modelodao;

import java.util.List;

import cajero.modelo.entitybean.Cuenta;

public interface CuentaDao {
	List <Cuenta>buscarTodas();
	Cuenta buscarUna(int idCuenta);
	int modificar (Cuenta cuenta);
}
