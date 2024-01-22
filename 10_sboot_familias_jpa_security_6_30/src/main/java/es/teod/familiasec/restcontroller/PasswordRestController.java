package es.teod.familiasec.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class PasswordRestController {
	
 	@Autowired
 	private PasswordEncoder passwordEncoder;

	@GetMapping("/encriptar/{pass}")  
	public String pruebaBcrypt(@PathVariable("pass") String password) {
	//	String password = "tomasin";
 		String encriptado = passwordEncoder.encode(password);  
	//	System.out.println("Password encriptado: " + encriptado);  
	 	return encriptado;
		
	}


}
