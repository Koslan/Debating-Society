package ua.step.debating.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.step.debating.models.Lobby;

public interface LobbyRepository extends JpaRepository<Lobby, Integer> {

}
