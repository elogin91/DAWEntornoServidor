package eventos.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eventos.modelo.entitis.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

}
