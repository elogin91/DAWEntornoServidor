package es.fp.restfull.fabrica.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.fp.restfull.fabrica.modelo.entitybeans.Familia;

public interface FamiliaRepository extends JpaRepository<Familia, Integer>{

}
