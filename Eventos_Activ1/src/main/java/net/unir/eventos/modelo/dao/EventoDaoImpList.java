package net.unir.eventos.modelo.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import net.unir.eventos.modelo.javabean.Evento;

@Repository
public class EventoDaoImpList implements EventoDao{
	
	private List<Evento> lista;
	private TipoDao tipoDao;
	private static int idAuto;
	
	static {
		idAuto=0;
	}
	
	public EventoDaoImpList() {
		lista = new ArrayList<>();
		tipoDao = new TipoDaoImpList();
		cargarLista();
	}
	
	private void cargarLista() {
		
		lista.add(new Evento(1, "Cumple Maria", "Cumpleaños con tarta, piñata y actuación", new Date(2024-01-15), 180, "Avd. España s/n",
				"Activo", 'N', 50, 10, 350, tipoDao.findById(3)));
		lista.add(new Evento(2, "Boda Pepe", "Paquete nocturno con fuegos artificiales", new Date(2023-10-10), 600, "Plaza Mayor",
				"Cancelado", 'S', 150, 50, 15000, tipoDao.findById(2)));
		lista.add(new Evento(3, "Concierto Rafael", "En el campo de futbol durante la noche", new Date(2024-06-23), 120, "Calle Málaga",
				"Activo", 'S', 40000, 10000, 1000000, tipoDao.findById(1) ));
		idAuto = 3;
		
	}

	@Override
	public Evento findById(int idEvento) {
		for (int i = 0; i < lista.size();i++) {
			if (lista.get(i).getIdEvento() == idEvento)
				return lista.get(i);
		}
		return null;
	}

	@Override
	public int insert(Evento evento) {
		if (!lista.contains(evento)) {
			evento.setIdEvento(++idAuto);
			lista.add(evento);
			return 1;
		}
		return 0;
	}

	@Override
	public int delete(int idEvento) {
		Evento evento = findById(idEvento);
		if (evento == null) 
			return 0;
		
		return lista.remove(evento) ? 1 : 0;
	}

	@Override
	public int updateOne(Evento evento) {
		int pos = lista.indexOf(evento);
		if (pos == -1) {
			return 0;
		}
		lista.set(pos, evento);
		return 1;
	}

	@Override
	public List<Evento> findActives() {
		return lista.stream()
				.filter(it -> it.getEstado().equalsIgnoreCase("activo"))
				.toList();
	}

	@Override
	public List<Evento> findNotActives() {
		return lista.stream()
				.filter(it -> it.getEstado().equalsIgnoreCase("cancelado"))
				.toList();
	}

}
