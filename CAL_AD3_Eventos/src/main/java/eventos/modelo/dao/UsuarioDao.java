package eventos.modelo.dao;

import eventos.modelo.entitis.Usuario;

public interface UsuarioDao {
	Usuario altaUsuario(Usuario usuario);
	Usuario modificarUsuario(Usuario usuario);
	Usuario buscarUnUsuario(Usuario usuario);
}
