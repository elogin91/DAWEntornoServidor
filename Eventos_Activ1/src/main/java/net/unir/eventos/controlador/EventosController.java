package net.unir.eventos.controlador;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.unir.eventos.modelo.dao.EventoDao;
import net.unir.eventos.modelo.dao.TipoDao;
import net.unir.eventos.modelo.javabean.Evento;

@RequestMapping("eventos")
@Controller
public class EventosController {

	@Autowired
	private EventoDao edao;
	@Autowired
	private TipoDao tdao;

	@GetMapping("/eliminar/{id}")
	public String eliminarEvento(@PathVariable("id") int idEvento, Model model) {
		if (edao.delete(idEvento) == 1)
			model.addAttribute("mensaje", "Evento eliminado correctamente");
		else
			model.addAttribute("mensaje", "Evento no eliminado");
		return "forward:/";
	}

	@GetMapping("/detalle/{id}")
	public String verEvento(@PathVariable("id") int idEvento, Model model) {
		Evento evento = edao.findById(idEvento);
		if (evento != null) {
			model.addAttribute("evento", evento);
			return "verDetalle";
		} else {
			model.addAttribute("mensaje", "El evento no existe");
			return "forward:/";
		}
	}

	@GetMapping("/alta")
	public String mostrarFormularioAlta(Model model) {
		
		model.addAttribute("evento", new Evento());
		model.addAttribute("tipos", tdao.findAll());
		return "formularioAlta";
	}

	@PostMapping("/alta")
	public String procesarFormularioAlta(Evento evento, RedirectAttributes ratt) {
		evento.setTipo(tdao.findById(evento.getTipo().getIdTipo()));
		if (edao.insert(evento) == 1) {
			ratt.addFlashAttribute("mensaje", "Evento dado de alta");
		} else {
			ratt.addFlashAttribute("mensaje", "Algo ha fallado, alta no realizada");
		}
		return "redirect:/";

	}

	@GetMapping("/editar/{id}")
	public String editarEvento(@PathVariable("id") int idEvento, Model model) {
		Evento evento = edao.findById(idEvento);
		if (evento != null) {
			model.addAttribute("evento", evento);
			model.addAttribute("tipos", tdao.findAll());

			return "formularioEdicion";
		} else {
			model.addAttribute("mensaje", "El evento no existe");
			return "forward:/";
		}
	}

	@PostMapping("/editar/{id}")
	public String procesarEditarEvento(Evento evento, @PathVariable("id") int idEvento, RedirectAttributes ratt) {
		evento.setIdEvento(idEvento);
		evento.setTipo(tdao.findById(evento.getTipo().getIdTipo()));

		if (edao.updateOne(evento) == 1) {
			ratt.addFlashAttribute("mensaje", "Evento modificado");
		} else {
			ratt.addFlashAttribute("mensaje", "Evento no modificado");
		}
		return "redirect:/";
	}

	@GetMapping("/cancelar/{id}")
	public String procesarCancelarEvento(@PathVariable("id") int idEvento, Model model) {
		if (edao.cancelEvent(idEvento) == 1)
			model.addAttribute("mensaje", "Evento cancelado correctamente");
		else
			model.addAttribute("mensaje", "Evento no cancelado");
		return "forward:/";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
}
