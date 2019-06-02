package ua.step.debating.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.step.debating.models.Theme;

public interface ThemeRepository extends JpaRepository<Theme, Integer>{

}
