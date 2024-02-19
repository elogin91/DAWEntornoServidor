package unir.exa.ventas.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import unir.exa.ventas.modelo.entity.Comercial;
import unir.exa.ventas.repository.ComercialRepository;

@Repository
public class ComercialDaoImpl implements ComercialDao{
	@Autowired
	ComercialRepository comrepo;
	
	@Override
	public Comercial altaComercial(Comercial comercial) {
		try {
			return comrepo.save(comercial);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public boolean eliminarComercial(int idComercial) {
		try {
			comrepo.deleteById(idComercial);
			return true;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	public List<Comercial> listarTodos() {
		return comrepo.findAll();
	}

	@Override
	public Comercial buscarUnComercial(int idComercial) {
		return comrepo.findById(idComercial).orElse(null);
	}

	@Override
	public List<Comercial> buscarComercialPorCliente(int idComercial) {
		return comrepo.buscarComercialByCliente(idComercial);
	}

	@Override
	public List<Comercial> buscarComercialesConPedidos() {
		return comrepo.comercialConPedidos();
	}



}
