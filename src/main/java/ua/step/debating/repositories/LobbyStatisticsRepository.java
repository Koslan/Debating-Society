package ua.step.debating.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.step.debating.models.LobbyStatistics;

public interface LobbyStatisticsRepository extends JpaRepository<LobbyStatistics, Integer>{

}
