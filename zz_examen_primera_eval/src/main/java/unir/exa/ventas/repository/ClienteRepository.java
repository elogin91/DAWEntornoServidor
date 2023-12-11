package unir.exa.ventas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import unir.exa.ventas.modelo.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
