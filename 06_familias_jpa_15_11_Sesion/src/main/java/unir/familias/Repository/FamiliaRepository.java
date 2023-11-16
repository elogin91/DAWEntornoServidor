package unir.familias.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import unir.familias.modelo.entity.Familia;

public interface FamiliaRepository extends JpaRepository<Familia, Integer>{

}
