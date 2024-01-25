package eventos.modelo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eventos.modelo.entitis.Usuario;
import eventos.modelo.repository.UsuarioRepository;

@Repository
public class UsuarioDaoImpl implements UsuarioDao{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public Usuario altaUsuario(Usuario usuario) {
		try {
			return usuarioRepository.save(usuario);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public Usuario modificarUsuario(Usuario usuario) {
		try {
			return usuarioRepository.save(usuario);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public Usuario buscarUnUsuario(Usuario usuario) {
		return usuarioRepository.findByUsername(usuario.getUsername());
	}

}
