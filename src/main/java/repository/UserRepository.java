package repository;

import model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    // попозже если понадобится добавлю методы получить всех юзеров по имени и тд к примеру
}
