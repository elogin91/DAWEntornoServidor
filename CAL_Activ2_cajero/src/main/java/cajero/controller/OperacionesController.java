package cajero.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@GetMapping("/extraer")
	public String extraer () {
		return "extraer";
	}
	
	@PostMapping("/extraer")
	public String procesarExtraer (RedirectAttributes ratt, HttpSession sesion, @RequestParam Double cantidad) {
			Integer idCuenta = (Integer) sesion.getAttribute("cuenta");
			Cuenta cuenta = cdao.buscarUna(idCuenta);
			cuenta = cdao.cargo(cuenta, cantidad);
			if ( cuenta!= null) {
				Movimiento movimiento =  new Movimiento(null, cuenta, new Date(), cantidad, "Extraer");
				mdao.altaUno(movimiento);
				ratt.addFlashAttribute("mensaje", " Ha extraido " + cantidad + " en su cuenta " + cuenta.getIdCuenta());

			} else {
				ratt.addFlashAttribute("mensaje", " No hay suficiente saldo en su cuenta.");
			}
			return "redirect:/menu";		
	}
	
	@GetMapping("/movimientos")
	public String listarMovimientos (Model model, HttpSession sesion) {
		Integer idCuenta = (Integer) sesion.getAttribute("cuenta");
		model.addAttribute("movimientos", mdao.movimientosDeUnaCuenta(idCuenta));
		model.addAttribute("cuenta", cdao.buscarUna(idCuenta));
		return "movimientos";
	}
	
	@GetMapping("/trans"
			+ "ferencia")
	public String transferencia () {
		return "transferencia";
	}
	
	@PostMapping("/transferencia")
	public String procesarTransferencia (RedirectAttributes ratt, HttpSession sesion, @RequestParam Double cantidad, @RequestParam Integer idCuentaDestino) {
			Integer idCuenta = (Integer) sesion.getAttribute("cuenta");
			Cuenta cuentaOrigen = cdao.buscarUna(idCuenta);
			Cuenta cuentaDestino = cdao.buscarUna(idCuentaDestino);
			
			if(cuentaDestino != null) {
				cuentaOrigen = cdao.cargo(cuentaOrigen, cantidad);
				if ( cuentaOrigen!= null) {
					Movimiento movimiento =  new Movimiento(null, cuentaOrigen, new Date(), cantidad, "Cargo por transferencia");
					mdao.altaUno(movimiento);
					cuentaDestino =cdao.reintegro(cuentaDestino, cantidad);
					Movimiento movimientoDestino =  new Movimiento(null, cuentaDestino, new Date(), cantidad, "Abono por transferencia");
					mdao.altaUno(movimientoDestino);
					ratt.addFlashAttribute("mensaje", "Transferencia realizada.");
				} else {
					ratt.addFlashAttribute("mensaje", " No hay suficiente saldo en su cuenta.");
				}
			}
			else {
				ratt.addFlashAttribute("mensaje", "Cuenta destino no existe.");
			}

			return "redirect:/menu";		
	}
}
