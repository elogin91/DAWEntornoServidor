package vacantes.modelo.dto;

import lombok.Data;
import vacantes.modelo.entidades.Usuario;

@Data
public class UsuarioDto {

	private String username;
	private String nombre;
	private String apellidos;
	private String email;
	
	public static UsuarioDto from(Usuario usuario) {
		UsuarioDto usuarioDto = new UsuarioDto();
		usuarioDto.setUsername(usuario.getUsername());
		usuarioDto.setNombre(usuario.getNombre());
		usuarioDto.setApellidos(usuario.getApellidos());
		usuarioDto.setEmail(usuario.getEmail());
		return usuarioDto;
	}
	
}
