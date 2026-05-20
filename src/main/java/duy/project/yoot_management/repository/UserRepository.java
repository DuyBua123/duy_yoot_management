package duy.project.yoot_management.repository;

import duy.project.yoot_management.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    java.util.Optional<User> findByUsername(String username);

}
