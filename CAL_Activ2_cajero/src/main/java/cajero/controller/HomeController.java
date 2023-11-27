package cajero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cajero.modelo.entitybean.Cuenta;
import cajero.modelodao.CuentaDao;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	@Autowired
	private CuentaDao cdao;

	@GetMapping("/")
	public String home() {

		return "formLogin";
	}

	@PostMapping("/login")
	public String procLogin(RedirectAttributes ratt, HttpSession sesion, @RequestParam Integer idCuenta) {
		Cuenta cuenta = cdao.buscarUna(idCuenta);
		if (cuenta != null) {
			sesion.setAttribute("cuenta", idCuenta);
			return "redirect:/menu";
		}
		ratt.addFlashAttribute("mensaje", "Cuenta no existe.");
		return "redirect:/";
	}

	@GetMapping("/menu")
	public String menu(HttpSession sesion) {
		if (sesion.getAttribute("cuenta") == null) {
			return "redirect:/";
		}
		return "menu";
	}

	@GetMapping("/logout")
	public String logOut(HttpSession sesion) {
		sesion.invalidate();
		return "formLogin";
	}
}
