package eventos.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import eventos.modelo.dao.EventoDao;
import eventos.modelo.dao.ReservaDao;
import eventos.modelo.dao.TipoDao;
import eventos.modelo.dao.UsuarioDao;
import eventos.modelo.entitis.Evento;
import eventos.modelo.entitis.Usuario;
import jakarta.servlet.http.HttpSession;

abstract class BaseController {

	@Autowired
	protected EventoDao eventoDao;
	
	@Autowired
	protected TipoDao tipoDao;
	
	@Autowired
	protected ReservaDao reservaDao;
	
	@Autowired
	protected UsuarioDao usuarioDao;
	
	protected List<Evento> filterTypes(List<Evento> eventos, String tipo) {
		List<Evento> eventosFiltrados = new ArrayList<>(eventos);
		if (tipo != null && !tipo.isBlank()) {
			eventosFiltrados.removeIf(evento -> evento.getTipo().getIdTipo() != Integer.parseInt(tipo));
		}
		return eventosFiltrados;
	}

	protected void setSessionAttributes(HttpSession httpSession, Authentication authentication) {
		httpSession.setAttribute("tipos", tipoDao.buscarTodosTipo());
		if (authentication != null) {
			Usuario usuario = usuarioDao.buscarUnUsuario(authentication.getName());
			httpSession.setAttribute("usuario", usuario.getNombre());
		}
	}
}
