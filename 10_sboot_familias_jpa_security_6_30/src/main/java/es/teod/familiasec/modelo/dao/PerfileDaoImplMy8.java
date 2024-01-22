package es.teod.familiasec.modelo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.teod.familiasec.modelo.beans.Perfile;
import es.teod.familiasec.modelo.repository.PerfileRepository;

@Repository
public class PerfileDaoImplMy8 implements PerfileDao{
	@Autowired
	PerfileRepository prepo;

	@Override
	public Perfile findById(int idPerfil) {
		// TODO Auto-generated method stub
		return prepo.findById(idPerfil).orElse(null);
	}
	
	

}
