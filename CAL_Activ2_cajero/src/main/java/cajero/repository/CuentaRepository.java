package cajero.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cajero.modelo.entitybean.Cuenta;

public interface CuentaRepository extends JpaRepository<Cuenta, Integer>{

}
