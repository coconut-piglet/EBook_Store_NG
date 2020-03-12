package se228.richard.ebookstore.dao;

import se228.richard.ebookstore.entity.User;

import java.util.List;

public interface UserDao {
    User findUserByUserid(int userid);
    User findUserByUsername(String username);
    User findUserByUsernameAndUserpwd(String username, String userpwd);
    void createNewUser(String username, String useremail, String userpwd);
    void modifyUser(User user);
    List<User> fetchUserList();
}
