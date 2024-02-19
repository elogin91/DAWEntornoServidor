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

/**
 * Controlador que maneja las operaciones relacionadas con las transacciones financieras de la aplicación cajero.
 */
@Controller
public class OperacionesController {
	
	// Inyección de dependencia para acceder a la capa de datos de cuentas
	@Autowired
	CuentaDao cdao;
	
	// Inyección de dependencia para acceder a la capa de datos de movimientos
	@Autowired
	MovimientoDao mdao;
	
	 /**
     * Manejador para la página de ingreso ("/ingresar") utilizando el método HTTP GET.
     * Verifica si hay una sesión activa y, en caso afirmativo, muestra la página de ingreso con el saldo actual.
     * Si no hay sesión activa, redirige a la página principal.
     *
     * @param sesion Sesión HTTP para verificar la existencia de una sesión activa.
     * @param model  Modelo utilizado para pasar datos a la vista.
     * @return La vista "ingresar" o redirige a la página principal si no hay sesión activa.
     */
	@GetMapping("/ingresar")
	public String ingresar (HttpSession sesion, Model model) {
		if (sesion.getAttribute("idCuenta") != null) {
			Integer idCuenta = (Integer) sesion.getAttribute("idCuenta");
			Cuenta cuenta = cdao.buscarUna(idCuenta);
			model.addAttribute("saldo", cuenta.getSaldo());
			return "ingresar";
		}
		else {
			return "redirect:/";
		}
	}
	
    /**
     * Manejador para procesar el formulario de ingreso ("/ingresar") utilizando el método HTTP POST.
     * Realiza un ingreso en la cuenta actual, cambiando su saldo, y registra el movimiento en la base de datos.
     *
     * @param ratt     Atributos de redirección para pasar mensajes entre redireccionamientos.
     * @param sesion   Sesión HTTP para obtener el número de cuenta del usuario.
     * @param cantidad Cantidad a ingresar en la cuenta.
     * @return La vista redirigida ("menu").
     */
	@PostMapping("/ingresar")
	public String procesarIngreso (RedirectAttributes ratt, HttpSession sesion, @RequestParam Double cantidad) {
			Integer idCuenta = (Integer) sesion.getAttribute("idCuenta");
			Cuenta cuenta = cdao.buscarUna(idCuenta);
			cuenta = cdao.reintegro(cuenta, cantidad);
			Movimiento movimiento =  new Movimiento(null, cuenta, new Date(), cantidad, "Ingresar");
			mdao.altaUno(movimiento);
			ratt.addFlashAttribute("mensaje", " Ha ingresado " + cantidad + " en su cuenta " + cuenta.getIdCuenta());
		return "redirect:/menu";
		
	}
	
	 /**
     * Manejador para la página de retirar saldo ("/extraer") utilizando el método HTTP GET.
     * Verifica si hay una sesión activa y, en caso afirmativo, muestra la página de retiro con el saldo actual.
     * Si no hay sesión activa, redirige a la página principal.
     *
     * @param sesion Sesión HTTP para verificar la existencia de una sesión activa.
     * @param model  Modelo utilizado para pasar datos a la vista.
     * @return La vista "extraer" o redirige a la página principal si no hay sesión activa.
     */
	@GetMapping("/extraer")
	public String extraer (HttpSession sesion, Model model) {
		if (sesion.getAttribute("idCuenta") != null) {
			Integer idCuenta = (Integer) sesion.getAttribute("idCuenta");
			Cuenta cuenta = cdao.buscarUna(idCuenta);
			model.addAttribute("saldo", cuenta.getSaldo());
			return "extraer";
		}
		else {
			return "redirect:/";
		}
	}
	
	/**
     * Manejador para procesar el formulario de retirar saldo ("/extraer") utilizando el método HTTP POST.
     * Realiza un retiro en la cuenta actual,cambia el saldo de la cuenta, y registra el movimiento en la base de datos si hay saldo suficiente.
     * Adiciona un mensaje de éxito o error a los atributos de redirección.
     *
     * @param ratt     Atributos de redirección para pasar mensajes entre redireccionamientos.
     * @param sesion   Sesión HTTP para obtener el número de cuenta del usuario.
     * @param cantidad Cantidad a retirar de la cuenta.
     * @return La vista redirigida ("menu").
     */
	@PostMapping("/extraer")
	public String procesarExtraer (RedirectAttributes ratt, HttpSession sesion, @RequestParam Double cantidad) {
			Integer idCuenta = (Integer) sesion.getAttribute("idCuenta");
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
	
	/**
     * Manejador para la página de listar movimientos ("/movimientos") utilizando el método HTTP GET.
     * Verifica si hay una sesión activa y, en caso afirmativo, muestra la página de movimientos con el saldo actual
     * y la lista de movimientos de la cuenta.
     * Si no hay sesión activa, redirige a la página principal.
     *
     * @param model  Modelo utilizado para pasar datos a la vista.
     * @param sesion Sesión HTTP para verificar la existencia de una sesión activa.
     * @return La vista "movimientos" o redirige a la página principal si no hay sesión activa.
     */
	@GetMapping("/movimientos")
	public String listarMovimientos (Model model, HttpSession sesion) {
		if (sesion.getAttribute("idCuenta") != null) {
			Integer idCuenta = (Integer) sesion.getAttribute("idCuenta");
			Cuenta cuenta = cdao.buscarUna(idCuenta);
			model.addAttribute("saldo", cuenta.getSaldo());
			model.addAttribute("movimientos", mdao.movimientosDeUnaCuenta(idCuenta));
			return "movimientos";
		}
		else {
			return "redirect:/";
		}
		
	}
	
	/**
     * Manejador para la página de transferencia ("/transferencia") utilizando el método HTTP GET.
     * Verifica si hay una sesión activa y, en caso afirmativo, muestra la página de transferencia con el saldo actual.
     * Si no hay sesión activa, redirige a la página principal.
     *
     * @param sesion Sesión HTTP para verificar la existencia de una sesión activa.
     * @param model  Modelo utilizado para pasar datos a la vista.
     * @return La vista "transferencia" o redirige a la página principal si no hay sesión activa.
     */
	@GetMapping("/transferencia")
	public String transferencia (HttpSession sesion, Model model) {
		if (sesion.getAttribute("idCuenta") != null) {
			Integer idCuenta = (Integer) sesion.getAttribute("idCuenta");
			Cuenta cuenta = cdao.buscarUna(idCuenta);
			model.addAttribute("saldo", cuenta.getSaldo());
			return "transferencia";
		}
		else {
			return "redirect:/";
		}
	}
	
	/**
     * Manejador para procesar el formulario de transferencia ("/transferencia") utilizando el método HTTP POST.
     * Realiza una transferencia entre cuentas, registra los movimientos en la base de datos y actualiza el saldo de ambas cuentas.
     * La transferencia no se puede realizar si el idCuenta de la cuenta de destino no existe o si la cuenta de origen tiene menos saldo que la cantidad a transferir.
     * Adiciona un mensaje de éxito o error a los atributos de redirección.
     *
     * @param ratt              Atributos de redirección para pasar mensajes entre redireccionamientos.
     * @param sesion            Sesión HTTP para obtener el número de cuenta del usuario.
     * @param cantidad          Cantidad a transferir entre cuentas.
     * @param idCuentaDestino   Número de cuenta de destino para la transferencia.
     * @return La vista redirigida ("menu").
     */
	@PostMapping("/transferencia")
	public String procesarTransferencia (RedirectAttributes ratt, HttpSession sesion, @RequestParam Double cantidad, @RequestParam Integer idCuentaDestino) {
			Integer idCuenta = (Integer) sesion.getAttribute("idCuenta");
			Cuenta cuentaOrigen = cdao.buscarUna(idCuenta);
			Cuenta cuentaDestino = cdao.buscarUna(idCuentaDestino);
			
			if(cuentaDestino != null) {
				if (cdao.transferenciaSaldo(cuentaDestino, cuentaOrigen, cantidad)) {
					Movimiento movimiento =  new Movimiento(null, cuentaOrigen, new Date(), cantidad, "Cargo por transferencia");
					mdao.altaUno(movimiento);
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
