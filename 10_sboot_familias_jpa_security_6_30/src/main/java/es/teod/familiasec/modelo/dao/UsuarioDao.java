package es.teod.familiasec.modelo.dao;

import es.teod.familiasec.modelo.beans.Usuario;

public interface UsuarioDao {
	
	Usuario findById(String username);
	boolean registro(Usuario usuario);
	

}
