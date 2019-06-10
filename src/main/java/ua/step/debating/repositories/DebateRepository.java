package ua.step.debating.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.step.debating.models.Debate;


/**
 * 
 * @author vital
 *
 */
public interface DebateRepository extends JpaRepository<Debate, Integer> {

}
