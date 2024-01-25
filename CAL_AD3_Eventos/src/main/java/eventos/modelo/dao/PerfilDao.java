package eventos.modelo.dao;

import java.util.List;

import eventos.modelo.entitis.Perfil;

public interface PerfilDao {
	Perfil altaPerfil(Perfil perfil);
	Perfil modificarPerfil(Perfil perfil);
	Perfil buscarUnoPerfil(Perfil perfil);
	List<Perfil> buscarTodosPerfil();
	
}
