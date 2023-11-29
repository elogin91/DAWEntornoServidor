package cajero.modelodao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cajero.modelo.entitybean.Cuenta;
import cajero.repository.CuentaRepository;

/**
 * Implementación del acceso a datos para operaciones relacionadas con cuentas
 * bancarias.
 */
@Repository
public class CuentaDaoImpl implements CuentaDao {
	
	// Inyección de dependencia para acceder a la capa de datos de cuentas de la base de datos
	@Autowired
	private CuentaRepository crepo;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Cuenta> buscarTodas() {
		return crepo.findAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Cuenta buscarUna(int idCuenta) {
		return crepo.findById(idCuenta).orElse(null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Cuenta reintegro(Cuenta cuenta, Double cantidad) {
		cuenta.setSaldo(cuenta.getSaldo() + cantidad);
		return crepo.save(cuenta);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Cuenta cargo(Cuenta cuenta, Double cantidad) {
		if (cuenta.getSaldo() >= cantidad) {
			cuenta.setSaldo(cuenta.getSaldo() - cantidad);
			return crepo.save(cuenta);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean transferenciaSaldo(Cuenta cuentaDestino, Cuenta cuentaOrigen, Double cantidad) {
		if (this.cargo(cuentaOrigen, cantidad) != null) {
			this.reintegro(cuentaDestino, cantidad);
			return true;
		}
		return false;
	}

}
