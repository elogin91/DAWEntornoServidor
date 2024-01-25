package eventos.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eventos.modelo.entitis.Tipo;
import eventos.modelo.repository.TipoRepository;

@Repository
public class TipoDaoImpl implements TipoDao {

	@Autowired
	private TipoRepository tipoRepository;

	@Override
	public Tipo altaTipo(Tipo tipo) {
		try {
			return tipoRepository.save(tipo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public Tipo modificarTipo(Tipo tipo) {
		try {
			return tipoRepository.save(tipo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public Tipo buscarUnTipo(Tipo tipo) {
		return tipoRepository.findById(tipo.getIdTipo()).orElse(null);
	}

	@Override
	public List<Tipo> buscarTodosTipo() {
		return tipoRepository.findAll();
	}

}
