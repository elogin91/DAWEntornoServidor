package eventos.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import eventos.modelo.dao.EventoDao;
import eventos.modelo.dao.PerfilDao;
import eventos.modelo.dao.ReservaDao;
import eventos.modelo.dao.TipoDao;
import eventos.modelo.dao.UsuarioDao;
import eventos.modelo.entitis.Evento;
import eventos.modelo.entitis.Usuario;
import jakarta.servlet.http.HttpSession;

/**
 * Clase abstracta que sirve como base para los controladores del sistema.
 * Proporciona funcionalidades comunes y métodos de utilidad para los controladores derivados.
 */
abstract class BaseController {

	@Autowired
	protected EventoDao eventoDao;
	
	@Autowired
	protected TipoDao tipoDao;
	
	@Autowired
	protected PerfilDao perfilDao;
	
	@Autowired
	protected ReservaDao reservaDao;
	
	@Autowired
	protected UsuarioDao usuarioDao;
	
	/**
     * Filtra una lista de eventos según el tipo especificado.
     *
     * @param eventos la lista de eventos a filtrar
     * @param tipo    el ID del tipo de evento a filtrar (puede ser nulo o vacío para no aplicar el filtro)
     * @return una lista de eventos filtrados según el tipo especificado
     */
	protected List<Evento> filterTypes(List<Evento> eventos, String tipo) {
		List<Evento> eventosFiltrados = new ArrayList<>(eventos);
		if (tipo != null && !tipo.isBlank()) {
			eventosFiltrados.removeIf(evento -> evento.getTipo().getIdTipo() != Integer.parseInt(tipo));
		}
		return eventosFiltrados;
	}

	/**
     * Establece los atributos de sesión HTTP, como los tipos de eventos y el nombre de usuario, si está autenticado.
     *
     * @param httpSession     la sesión HTTP
     * @param authentication la información de autenticación del usuario (puede ser nulo si no está autenticado)
     */
	protected void setSessionAttributes(HttpSession httpSession, Authentication authentication) {
		httpSession.setAttribute("tipos", tipoDao.buscarTodosTipo());
		if (authentication != null) {
			Usuario usuario = usuarioDao.buscarUnUsuario(authentication.getName());
			httpSession.setAttribute("usuario", usuario.getNombre());
		}
	}
}
