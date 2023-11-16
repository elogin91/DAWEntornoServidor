package cajero.modelodao;

import java.util.List;

import cajero.modelo.entitybean.Movimiento;

public interface MovimientoDao {
	List<Movimiento> movimientosDeUnaCuenta(int idCuenta);
}
