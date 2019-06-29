package ua.step.debating.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.step.debating.models.User;

public interface UserRepository extends JpaRepository<User, Integer>  {
    Optional<User> findByLogin(String login);
    User findUserById(Integer id);
}
