package net.unir.eventos.modelo.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import net.unir.eventos.modelo.javabean.Evento;
import net.unir.eventos.modelo.javabean.Tipo;

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
		
		TipoDao tdao;
		
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Evento> findAll() {

		return lista;
	}

	@Override
	public int insert(Evento evento) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int idEvento) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateOne(Evento evento) {
		// TODO Auto-generated method stub
		return 0;
	}

}
