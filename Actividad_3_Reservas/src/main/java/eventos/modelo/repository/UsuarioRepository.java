package eventos.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eventos.modelo.entitis.Usuario;


/**
 * Repositorio para la entidad Usuario que proporciona métodos de consulta personalizados.
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	
	/**
     * Encuentra un usuario por su nombre de usuario.
     *
     * @param username el nombre de usuario del usuario a buscar
     * @return el usuario encontrado, o null si no se encuentra ninguno con el nombre de usuario especificado
     */
	public Usuario findByUsername(String username);
}
