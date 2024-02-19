package unir.familias.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import unir.familias.Repository.FamiliaRepository;
import unir.familias.modelo.entity.Familia;

@Repository
public class FamiliaDaoImplMy8Jpa implements FamiliaDao{
	
	@Autowired
	private FamiliaRepository frepo;

	@Override
	public Familia buscarUna(int idFamilia) {
		// TODO Auto-generated method stub
		return frepo.findById(idFamilia).orElse(null);
	}

	@Override
	public List<Familia> todas() {
		// TODO Auto-generated method stub
		return frepo.findAll();
	}

}
