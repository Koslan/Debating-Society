package ua.step.debating.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.step.debating.models.Vote;

public interface VoteRepository extends JpaRepository<Vote, Integer>  {

}