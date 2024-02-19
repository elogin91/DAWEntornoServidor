package cajero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cajero.modelo.entitybean.Cuenta;
import cajero.modelodao.CuentaDao;
import jakarta.servlet.http.HttpSession;

/**
 * Controlador que maneja las operaciones relacionadas con el inicio de sesión y menú del cajero.
 */
@Controller
public class HomeController {
	
	// Inyección de dependencia para acceder a la capa de datos de cuentas
	@Autowired
	private CuentaDao cdao;

	/**
     * Manejador para la ruta principal ("/") utilizando el método HTTP GET.
     * Devuelve la vista "formLogin" al acceder a la ruta principal.
     *
     * @return La vista "formLogin"
     */
	@GetMapping("/")
	public String home() {

		return "formLogin";
	}

	/**
     * Manejador para el proceso de inicio de sesión ("/login") utilizando el método HTTP POST.
     * Busca la cuenta en la base de datos a partir del número de cuenta proporcionado.
     * Si la cuenta existe, almacena el número de cuenta en la sesión y redirige a la página de menú.
     * Si la cuenta no existe, redirige a la página principal con un mensaje de error.
     *
     * @param ratt     Atributos de redirección para pasar mensajes entre redireccionamientos.
     * @param sesion   Sesión HTTP para almacenar el número de cuenta.
     * @param idCuenta Número de cuenta proporcionado en el formulario.
     * @return La vista redirigida ("menu" o "formLogin")
     */
	@PostMapping("/login")
	public String procLogin(RedirectAttributes ratt, HttpSession sesion, @RequestParam Integer idCuenta) {
		Cuenta cuenta = cdao.buscarUna(idCuenta);
		if (cuenta != null) {
			sesion.setAttribute("idCuenta", idCuenta);
			return "redirect:/menu";
		}
		ratt.addFlashAttribute("mensaje", "La cuenta introducida no existe.");
		return "redirect:/";
	}

	/**
     * Manejador para la página del menú ("/menu") utilizando el método HTTP GET.
     * Verifica si hay una sesión activa, si no, redirige a la página principal.
     * Obtiene el número de cuenta de la sesión y busca la cuenta correspondiente en la base de datos.
     * Agrega el saldo de la cuenta al modelo para ser mostrado en la vista.
     *
     * @param sesion Sesión HTTP para verificar la existencia de una sesión activa y obtener el número de cuenta.
     * @param model  Modelo utilizado para pasar datos a la vista.
     * @return La vista "menu" o redirige a la página principal si no hay sesión activa.
     */
	@GetMapping("/menu")
	public String menu(HttpSession sesion, Model model) {
		if (sesion.getAttribute("idCuenta") == null) {
			return "redirect:/";
		}
		Integer idCuenta = (Integer) sesion.getAttribute("idCuenta");
		Cuenta cuenta = cdao.buscarUna(idCuenta);
		model.addAttribute("saldo", cuenta.getSaldo());
		return "menu";
	}

	/**
     * Manejador para cerrar sesión ("/logout") utilizando el método HTTP GET.
     * Elimina la cuenta de la sesión, invalida la sesión y redirige a la página principal.
     *
     * @param sesion Sesión HTTP para eliminar la cuenta y invalidar la sesión.
     * @return La vista "formLogin"
     */
	@GetMapping("/logout")
	public String logOut(HttpSession sesion) {
		sesion.removeAttribute("cuenta");
		sesion.invalidate();
		return "formLogin";
	}
}
