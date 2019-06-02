package ua.step.debating.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.step.debating.models.Message;

public interface MessageRepository extends JpaRepository<Message, Integer>{

}
