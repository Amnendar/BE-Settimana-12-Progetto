package it.be.gestionecatalogo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.be.gestionecatalogo.model.Role;
import it.be.gestionecatalogo.model.Roles;



public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByRoleName(Roles role);
}
