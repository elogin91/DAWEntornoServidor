package net.unir.clientes.restapicontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PruebaSaludo {

	@GetMapping("/saludo")
	public String saludar(){
		return "Hola me llamo Cristina";
	}
}
