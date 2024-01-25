package eventos.modelo.dao;

import java.util.List;

import eventos.modelo.entitis.Tipo;

public interface TipoDao {
	Tipo altaTipo(Tipo tipo);
	Tipo modificarTipo(Tipo tipo);
	Tipo buscarUnTipo(Tipo tipo);
	List<Tipo> buscarTodosTipo();
}
