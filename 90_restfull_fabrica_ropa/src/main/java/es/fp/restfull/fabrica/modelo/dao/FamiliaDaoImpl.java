package es.fp.restfull.fabrica.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.fp.restfull.fabrica.modelo.entitybeans.Familia;
import es.fp.restfull.fabrica.modelo.repository.FamiliaRepository;

@Repository
public class FamiliaDaoImpl implements FamiliaDao{
	
	@Autowired
	private FamiliaRepository frepo;

	@Override
	public List<Familia> findAll() {
		// TODO Auto-generated method stub
		return frepo.findAll();
	}

	@Override
	public Familia verUna(int idFamilia) {
		// TODO Auto-generated method stub
		return frepo.findById(idFamilia).orElse(null);
	}

	@Override
	public int insertar(Familia familia) {
		if(verUna(familia.getIdFamilia()) == null) {
			frepo.save(familia);
			return 1;
		}else
			return 0;
		
		
	}

	@Override
	public int modificar(Familia familia) {
		if(verUna(familia.getIdFamilia()) != null) {
			frepo.save(familia);
			return 1;
		}else
			return 0;
	}

	@Override
	public int eliminar(int idFamilia) {
		if(verUna(idFamilia) != null) {
			frepo.deleteById(idFamilia);
			return 1;
		}else
			return 0;
	}
	
	
	

}
