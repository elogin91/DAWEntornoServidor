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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cuenta buscarUna(int idCuenta) {
		return crepo.findById(idCuenta).orElse(null);
	}

	@Override
	public int modificar(Cuenta cuenta) {
		// TODO Auto-generated method stub
		return 0;
	}

}
