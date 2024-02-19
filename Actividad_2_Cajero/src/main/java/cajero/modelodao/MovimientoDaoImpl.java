package cajero.modelodao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cajero.modelo.entitybean.Movimiento;
import cajero.repository.MovimientoRepository;


/**
 * Implementación del acceso a datos para operaciones relacionadas con movimientos bancarios.
 */
@Repository
public class MovimientoDaoImpl implements MovimientoDao{
	
	// Inyección de dependencia para acceder a la capa de datos de los movimientos de la base de datos
	@Autowired
	MovimientoRepository mrepo;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Movimiento> movimientosDeUnaCuenta(int idCuenta) {
		return mrepo.findMovimientoPorCuenta(idCuenta);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Movimiento buscarUno(int idMovimiento) {
		return mrepo.findById(idMovimiento).orElse(null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Movimiento altaUno(Movimiento movimiento) {
		try {
			return mrepo.save(movimiento);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Movimiento> buscarTodos() {
		return mrepo.findAll();
	}

}
