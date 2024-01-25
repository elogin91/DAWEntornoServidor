package eventos.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eventos.modelo.entitis.Perfil;
import eventos.modelo.repository.PerfilRepository;

@Repository
public class PerfilDaoImpl implements PerfilDao {
	@Autowired
	private PerfilRepository perfilRepository;

	@Override
	public Perfil altaPerfil(Perfil perfil) {
		try {
			return perfilRepository.save(perfil);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public Perfil modificarPerfil(Perfil perfil) {
		try {
			return perfilRepository.save(perfil);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public Perfil buscarUnoPerfil(Perfil perfil) {
		return perfilRepository.findById(perfil.getIdPerfil()).orElse(null);
	}

	@Override
	public List<Perfil> buscarTodosPerfil() {
		return perfilRepository.findAll();
	}

}
