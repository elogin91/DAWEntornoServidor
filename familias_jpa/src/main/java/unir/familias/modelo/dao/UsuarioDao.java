package unir.familias.modelo.dao;

import unir.familias.modelo.entity.Usuario;

public interface UsuarioDao {
	
	Usuario login(String username, String password);

}
