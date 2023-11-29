package cajero.modelodao;

import java.util.List;

import cajero.modelo.entitybean.Movimiento;

/**
 * Interfaz para el acceso a datos de movimientos bancarios.
 */
public interface MovimientoDao {
	
	 /**
     * Recupera todos los movimientos asociados a una cuenta específica.
     *
     * @param idCuenta Identificador único de la cuenta.
     * @return Lista de movimientos asociados a la cuenta.
     */
	List<Movimiento> movimientosDeUnaCuenta(int idCuenta);
	
	/**
     * Busca un movimiento específico por su identificador único.
     *
     * @param idMovimiento Identificador único del movimiento.
     * @return El movimiento encontrado o null si no existe.
     */
	Movimiento buscarUno (int idMovimiento);
	
	/**
     * Registra un nuevo movimiento en la base de datos.
     *
     * @param movimiento El movimiento a registrar.
     * @return El movimiento registrado con su identificador único asignado.
     */
	Movimiento altaUno (Movimiento movimiento);
	
	/**
     * Recupera todos los movimientos almacenados en la base de datos.
     *
     * @return Lista de todos los movimientos.
     */
	List<Movimiento> buscarTodos();
}
