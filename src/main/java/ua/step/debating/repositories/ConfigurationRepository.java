package ua.step.debating.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.step.debating.models.Configuration;

public interface ConfigurationRepository extends JpaRepository<Configuration, Integer>{

}
