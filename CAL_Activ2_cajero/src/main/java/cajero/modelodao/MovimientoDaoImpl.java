package cajero.modelodao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cajero.modelo.entitybean.Movimiento;
import cajero.repository.MovimientoRepository;


@Repository
public class MovimientoDaoImpl implements MovimientoDao{
	@Autowired
	MovimientoRepository mrepo;
	
	@Override
	public List<Movimiento> movimientosDeUnaCuenta(int idCuenta) {
		return mrepo.findMovimientoPorCuenta(idCuenta);
	}

	@Override
	public Movimiento buscarUno(int idMovimiento) {
		return mrepo.findById(idMovimiento).orElse(null);
	}

	@Override
	public Movimiento altaUno(Movimiento movimiento) {
		try {
			return mrepo.save(movimiento);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public List<Movimiento> buscarTodos() {
		return mrepo.findAll();
	}

}
