package cajero.modelodao;

import java.util.List;

import cajero.modelo.entitybean.Cuenta;

/**
 * Interfaz para el acceso a datos de cuentas bancarias.
 */
public interface CuentaDao {
	/**
	 * Recupera todas las cuentas almacenadas en la base de datos.
	 *
	 * @return Lista de todas las cuentas.
	 */
	List<Cuenta> buscarTodas();

	 /**
     * Busca una cuenta específica por su identificador único.
     *
     * @param idCuenta Identificador único de la cuenta.
     * @return La cuenta encontrada o null si no existe.
     */
	Cuenta buscarUna(int idCuenta);

	 /**
     * Realiza una operación de reintegro en una cuenta, aumentando su saldo.
     *
     * @param cuenta   La cuenta en la que se realiza el reintegro.
     * @param cantidad La cantidad a reintegrar en la cuenta.
     * @return La cuenta actualizada después del reintegro.
     */
	Cuenta reintegro(Cuenta cuenta, Double cantidad);

	 /**
     * Realiza una operación de cargo en una cuenta, disminuyendo su saldo si hay saldo suficiente.
     *
     * @param cuenta   La cuenta en la que se realiza el cargo.
     * @param cantidad La cantidad a cargar en la cuenta.
     * @return La cuenta actualizada después del cargo o null si no hay saldo suficiente.
     */
	Cuenta cargo(Cuenta cuenta, Double cantidad);
	
	/**
     * Realiza una transferencia de saldo entre dos cuentas.
     * Realiza un cargo en la cuenta de origen y un reintegro en la cuenta de destino.
     *
     * @param cuentaDestino Cuenta de destino para la transferencia.
     * @param cuentaOrigen  Cuenta de origen para la transferencia.
     * @param cantidad      La cantidad a transferir.
     * @return true si la transferencia se realiza con éxito, false si no hay saldo suficiente en la cuenta de origen.
     */
	Boolean transferenciaSaldo(Cuenta cuentaDestino, Cuenta cuentaOrigen, Double cantidad);
}
