package es.teod.familiasec.modelo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.teod.familiasec.modelo.beans.Usuario;
import es.teod.familiasec.modelo.repository.UsuarioRepository;

@Repository
public class UsuarioDaoImplMy8 implements UsuarioDao {
	@Autowired
	private UsuarioRepository urepo;
	@Override
	public Usuario findById(String username) {
		// TODO Auto-generated method stub
		return urepo.findById(username).orElse(null);
	}
	@Override
	public boolean registro(Usuario usuario) {
		if (findById(usuario.getUsername()) == null) {
				urepo.save(usuario);
				return true;
		}
		return false;
	}
}
