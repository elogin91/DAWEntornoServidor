package unir.familias.modelo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import unir.familias.Repository.UsuarioRepository;
import unir.familias.modelo.entity.Usuario;

@Repository
public class UsuarioDaoImplJpaMy8 implements UsuarioDao{
	
	@Autowired
	private UsuarioRepository urepo;

	@Override
	public Usuario login(String username, String password) {
		// TODO Auto-generated method stub
		return urepo.usuarioPorUsernameAndPassword(username, password);
	}
	
	
	

}
