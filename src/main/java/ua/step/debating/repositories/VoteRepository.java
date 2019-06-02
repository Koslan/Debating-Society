package ua.step.debating.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.step.debating.models.User;

public interface VoteRepository extends JpaRepository<User, Integer>  {

}