package es.teod.familiasec.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import es.teod.familiasec.modelo.beans.Producto;
import es.teod.familiasec.modelo.beans.Usuario;
import es.teod.familiasec.modelo.dao.IntProductoDao;
import es.teod.familiasec.modelo.dao.PerfileDao;
import es.teod.familiasec.modelo.dao.UsuarioDao;
import jakarta.servlet.http.HttpSession;



@Controller
public class HomeController {
	@Autowired
	IntProductoDao pdao;
	@Autowired
	UsuarioDao udao;
	@Autowired
	PerfileDao pfdao;
 	@Autowired
 	private PasswordEncoder passwordEncoder;
	
	
	@GetMapping("/")
	public String verTodos(Model model, Authentication aut) {
		
	//	System.out.println(aut.getName() + "  -  " + aut.getAuthorities());
		List<Producto> lista = pdao.buscarTodos();
		model.addAttribute("listaTodos", lista);
		
		return "listaProductos";
		 
		
	}
	@GetMapping("/signup")
	public String registrar(Model model) {
		
		
	model.addAttribute("usuario", new Usuario());
		
		return "registro";
		 
		
	}
	@PostMapping("/signup")
	public String proregistrar(Model model, Usuario usuario, RedirectAttributes ratt) {
		
		usuario.setEnabled(1);
		usuario.setFechaRegistro(new Date());
	 	usuario.addPerfil(pfdao.findById(3));
	  	usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
	 //	usuario.setPassword("{noop}"+ usuario.getPassword());
	 	if (udao.registro(usuario)) {
	 		ratt.addFlashAttribute("mensaje", "alta realizada");
	 		return "redirect:/login";
	 	}
	 	else {
	 		model.addAttribute("mensaje", "ya existe como usuario");
	 		return "/registro";
	 		
	 	}
		
	}
	
	@GetMapping("/error")
	public String procesarError() {
		
		 
		System.out.println("procesar error");
		
		return "pruebas";
	}
	
	@GetMapping("/index")
	public String procesarLogin(Authentication aut, Model model, HttpSession misesion) {
		
		System.out.println("usuario : " + aut.getName());
		Usuario usuario = udao.findById(aut.getName());
		
		if (misesion.getAttribute("usuario") == null)
			misesion.setAttribute("usuario", usuario);
		
		System.out.println();
		
		for (GrantedAuthority ele: aut.getAuthorities())
			System.out.println("ROL : " + ele.getAuthority());
		
		model.addAttribute("mensaje", aut.getAuthorities());
		
		
		return "redirect:/";
	}
	
	

}
