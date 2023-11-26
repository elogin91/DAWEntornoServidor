package cajero.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cajero.modelo.entitybean.Cuenta;
import cajero.modelo.entitybean.Movimiento;
import cajero.modelodao.CuentaDao;
import cajero.modelodao.MovimientoDao;
import jakarta.servlet.http.HttpSession;

@Controller
public class OperacionesController {
	
	@Autowired
	CuentaDao cdao;
	@Autowired
	MovimientoDao mdao;
	
	@GetMapping("/ingresar")
	public String ingresar () {
		return "ingresar";
	}
	@PostMapping("/ingresar")
	public String procesarIngreso (RedirectAttributes ratt, HttpSession sesion, @RequestParam Double cantidad) {
			Integer idCuenta = (Integer) sesion.getAttribute("cuenta");
			Cuenta cuenta = cdao.buscarUna(idCuenta);
			cuenta = cdao.reintegro(cuenta, cantidad);
			Movimiento movimiento =  new Movimiento(null, cuenta, new Date(), cantidad, "Ingresar");
			mdao.altaUno(movimiento);
			ratt.addFlashAttribute("mensaje", " Ha ingresado " + cantidad + " en su cuenta " + cuenta.getIdCuenta());
		return "redirect:/menu";
		
	}
}
