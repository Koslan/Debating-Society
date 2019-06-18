package ua.step.debating.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.step.debating.models.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	List<Role> findAllById(Set<Long> singleton);

}