package ua.step.debating.servises;

import java.util.Optional;

import ua.step.debating.models.User;

public interface UserService {
	void save(User user);

	Optional<User> findByLogin(String login);
}