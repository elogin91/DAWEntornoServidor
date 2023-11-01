package net.unir.eventos.modelo.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import net.unir.eventos.modelo.javabean.Estado;
import net.unir.eventos.modelo.javabean.Evento;

/**
 * Esta clase implementa la interfaz EventoDao y proporciona una implementación
 * basada en una lista en memoria.
 */
@Repository
public class EventoDaoImpList implements EventoDao {

	/**
	 * Lista que almacena los eventos.
	 */
	private List<Evento> lista;

	/**
	 * Instancia de TipoDao para obtener información de tipos de eventos.
	 */
	private TipoDao tipoDao;

	/**
	 * ID autoincremental para nuevos eventos.
	 */
	private static int idAuto;

	static {
		idAuto = 0;
	}

	/**
	 * Constructor de la clase. Inicializa la lista, el TipoDao y carga eventos de
	 * ejemplo.
	 */
	public EventoDaoImpList() {
		lista = new ArrayList<>();
		tipoDao = new TipoDaoImpList();
		cargarLista();
	}

	/**
	 * Método privado que carga eventos de ejemplo en la lista.
	 */
	private void cargarLista() {
		lista.add(new Evento(1, "Cumple Maria", "Cumpleaños con tarta, piñata y actuación", new Date(2024 - 01 - 15),
				180, "Avd. España s/n", Estado.ACTIVO, 'N', 50, 10, 350., tipoDao.findById(3)));
		lista.add(new Evento(2, "Boda Pepe", "Paquete nocturno con fuegos artificiales", new Date(2023 - 10 - 10), 600,
				"Plaza Mayor", Estado.CANCELADO, 'S', 150, 50, 15000., tipoDao.findById(2)));
		lista.add(new Evento(3, "Concierto Rafael", "En el campo de futbol durante la noche", new Date(2024 - 06 - 23),
				120, "Calle Málaga", Estado.ACTIVO, 'S', 40000, 10000, 1000000., tipoDao.findById(1)));
		idAuto = 3;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Evento findById(int idEvento) {
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getIdEvento() == idEvento)
				return lista.get(i);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int insert(Evento evento) {
		if (!lista.contains(evento)) {
			evento.setIdEvento(++idAuto);
			lista.add(evento);
			return 1;
		}
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int delete(int idEvento) {
		Evento evento = findById(idEvento);
		if (evento == null) {
			return 0;
		}
		return lista.remove(evento) ? 1 : 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int updateOne(Evento evento) {
		int result = lista.indexOf(evento);
		if (result != -1) {
			lista.set(result, evento);
			return 1;
		}
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Evento> findActives() {
		return lista.stream().filter(it -> it.getEstado() == Estado.ACTIVO).toList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Evento> findNotActives() {
		return lista.stream().filter(it -> it.getEstado() == Estado.CANCELADO).toList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int cancelEvent(int idEvento) {
		Evento evento = findById(idEvento);
		if (evento == null) {
			return 0;
		}
		evento.setEstado(Estado.CANCELADO);
		return 1;
	}

}
