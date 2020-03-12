package se228.richard.ebookstore.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import se228.richard.ebookstore.dao.UserDao;
import se228.richard.ebookstore.entity.User;
import se228.richard.ebookstore.repository.UserRepo;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private UserRepo userRepo;

    @Override
    public User findUserByUserid(int userid) {return userRepo.findUserByUserid(userid);}

    @Override
    public User findUserByUsername(String username) {
        return userRepo.findUserByUsername(username);
    }

    @Override
    public User findUserByUsernameAndUserpwd(String username, String userpwd) {
        return userRepo.findUserByUsernameAndUserpwd(username, userpwd);
    }

    @Override
    public void createNewUser(String username, String useremail, String userpwd) {
        User newuser = new User(username, useremail, userpwd,1);
        userRepo.save(newuser);
    }

    @Override
    public void modifyUser(User user) {
        userRepo.save(user);
    }

    @Override
    public List<User> fetchUserList() {
        return userRepo.findAll();
    }
}
