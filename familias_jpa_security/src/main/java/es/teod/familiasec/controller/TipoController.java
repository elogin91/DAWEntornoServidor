package es.teod.familiasec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app/tipos")
public class TipoController {
	
	@GetMapping("/todos") 
		public String todosUsuarios(Model model) {
			model.addAttribute("mensaje", "Listado de tipos");
			return "pruebas";
				
		}
	 

}
