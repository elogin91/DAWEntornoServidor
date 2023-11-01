package net.unir.eventos.modelo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import net.unir.eventos.modelo.javabean.Tipo;

/**
 * Esta clase implementa la interfaz TipoDao y proporciona una implementación
 * basada en una lista en memoria.
 */
@Repository
public class TipoDaoImpList implements TipoDao {

	/**
	 * Lista que almacena los tipos de eventos.
	 */
	private List<Tipo> lista;

	/**
	 * Constructor de la clase. Inicializa la lista y carga los tipos de eventos.
	 */
	public TipoDaoImpList() {
		lista = new ArrayList<>();
		cargarLista();
	}

	/**
	 * Método privado que carga los tipos de eventos en la lista.
	 */
	private void cargarLista() {
		lista.add(new Tipo(1, "Cumpleaños", "Celebración con amigos y familiares con muchas chuches"));
		lista.add(new Tipo(2, "Boda", "Celebración, espectáculo y comida"));
		lista.add(new Tipo(3, "Concierto", "Espectáculos musicales"));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Tipo> findAll() {
		return lista;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Tipo findById(int idTipo) {
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getIdTipo() == idTipo)
				return lista.get(i);
		}
		return null;
	}
}
