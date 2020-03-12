package se228.richard.ebookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se228.richard.ebookstore.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {
    User findUserByUserid(int userid);
    User findUserByUsername(String username);
    User findUserByUsernameAndUserpwd(String username, String userpwd);
}
