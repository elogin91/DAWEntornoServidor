package es.teod.familiasec.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.teod.familiasec.modelo.beans.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, String>{

}
