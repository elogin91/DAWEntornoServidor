package vacantes.modelo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vacantes.modelo.entidades.Usuario;
import vacantes.modelo.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	UsuarioRepository usuarioRepository;
	
	public Usuario buscarUnUsuario (String username) {
		return usuarioRepository.findById(username).orElse(null);
	}
	
	public Usuario modificarUnUsuario (Usuario usuario) {
		if(buscarUnUsuario(usuario.getUsername()) != null) {
			return usuarioRepository.save(usuario);
		}
		return null;
	}
}
