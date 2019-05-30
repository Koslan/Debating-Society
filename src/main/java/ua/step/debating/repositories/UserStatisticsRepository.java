package ua.step.debating.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.step.debating.models.UserStatistics;

public interface UserStatisticsRepository extends JpaRepository<UserStatistics, Integer>{

}
