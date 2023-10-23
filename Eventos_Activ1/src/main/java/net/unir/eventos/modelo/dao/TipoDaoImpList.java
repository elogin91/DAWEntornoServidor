package net.unir.eventos.modelo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import net.unir.eventos.modelo.javabean.Evento;
import net.unir.eventos.modelo.javabean.Tipo;

@Repository
public class TipoDaoImpList implements TipoDao{
	private List<Tipo> lista;
	
	public TipoDaoImpList() {
		lista = new ArrayList<>();
		cargarLista();
	}
	
	private void cargarLista() {
		lista.add(new Tipo(1, "Cumpleaños", "Celebración con amigos y familiares con muchas chuches"));
		lista.add(new Tipo(2, "Boda", "Celebración, espectáculo y comida"));
		lista.add(new Tipo(3, "Concierto", "Espectáculos musicales"));
	}

	@Override
	public List<Tipo> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tipo findById(int idTipo) {
		for (int i = 0; i < lista.size();i++) {
			if (lista.get(i).getIdTipo() == idTipo)
				return lista.get(i);
		}
		return null;
	}

}
