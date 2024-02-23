package vacantes.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vacantes.modelo.entidades.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

}
