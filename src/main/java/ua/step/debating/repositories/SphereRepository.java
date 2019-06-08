package ua.step.debating.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.step.debating.models.Sphere;

public interface SphereRepository extends JpaRepository<Sphere, Integer> {
	

}