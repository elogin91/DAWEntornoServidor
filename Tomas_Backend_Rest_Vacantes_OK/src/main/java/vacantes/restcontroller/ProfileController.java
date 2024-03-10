package vacantes.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vacantes.modelo.dto.UsuarioDto;
import vacantes.modelo.entidades.Usuario;
import vacantes.modelo.service.UserDetailsImpl;
import vacantes.modelo.service.UsuarioService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/profile")
public class ProfileController {

	@Autowired
	UsuarioService usuarioService;

	@GetMapping("")
	public ResponseEntity<?> viendoPerfilUsuario(Authentication authentication) {
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		Usuario usuario = usuarioService.buscarUnUsuario(userDetails.getUsername());
		UsuarioDto usuarioDto = UsuarioDto.from(usuario);

		return ResponseEntity.status(HttpStatus.OK).body(usuarioDto);
	}

	@PutMapping("/modificar")
	public ResponseEntity<?> modificandoPerfilUsuario(Authentication authentication, @RequestBody UsuarioDto usuarioDto) {
		Usuario usuario = usuarioService.buscarUnUsuario(usuarioDto.getUsername());
		usuario.setNombre(usuarioDto.getNombre());
		usuario.setApellidos(usuarioDto.getApellidos());
		usuario.setEmail(usuarioDto.getEmail());
		if (usuarioService.modificarUnUsuario(usuario) != null) {
			return ResponseEntity.status(HttpStatus.OK).body("Usuario modificado");
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario NO modificado");
	}

}
