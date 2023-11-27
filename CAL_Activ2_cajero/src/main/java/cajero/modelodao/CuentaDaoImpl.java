package cajero.modelodao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cajero.modelo.entitybean.Cuenta;
import cajero.repository.CuentaRepository;

@Repository
public class CuentaDaoImpl implements CuentaDao{
	
	@Autowired
	private CuentaRepository crepo;

	@Override
	public List<Cuenta> buscarTodas() {
		return crepo.findAll();
	}

	@Override
	public Cuenta buscarUna(int idCuenta) {
		return crepo.findById(idCuenta).orElse(null);
	}

	@Override
	public Cuenta reintegro(Cuenta cuenta, Double cantidad) {
		cuenta.setSaldo(cuenta.getSaldo() + cantidad);
		return crepo.save(cuenta);
	}

	@Override
	public Cuenta cargo(Cuenta cuenta, Double cantidad) {
		if (comprobarSaldoSuficiente(cuenta, cantidad) ) {
			cuenta.setSaldo(cuenta.getSaldo() - cantidad);
			return crepo.save(cuenta);
		}
		return null;
	}

	private boolean comprobarSaldoSuficiente(Cuenta cuenta, Double cantidad) {
		return cuenta.getSaldo()>=cantidad;
	}

}
