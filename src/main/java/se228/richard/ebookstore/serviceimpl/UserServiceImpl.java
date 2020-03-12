package se228.richard.ebookstore.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import se228.richard.ebookstore.dao.AdminDao;
import se228.richard.ebookstore.dao.UserDao;
import se228.richard.ebookstore.entity.Admin;
import se228.richard.ebookstore.entity.Message;
import se228.richard.ebookstore.entity.User;
import se228.richard.ebookstore.service.UserService;

@Service
@Scope("session")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private AdminDao adminDao;

    @Override
    public Message check_availability(String usernmae) {
        User user = userDao.findUserByUsername(usernmae);
        Admin admin = adminDao.findAdminByAdminname(usernmae);
        if (user == null && admin == null) {
            return new Message("PASS", usernmae + "can be used");
        }
        else {
            return new Message("FAIL", "username <" + usernmae +"> already used");
        }
    }

    @Override
    public Message verify_credential(String username, String userpwd) {
        if (username.trim().equals("") || userpwd.trim().equals("")) {
            return new Message("FAIL", "wrong username or password");
        }
        User user = userDao.findUserByUsernameAndUserpwd(username, userpwd);
        Admin admin = adminDao.findAdminByAdminnameAndAdminpwd(username, userpwd);
        if (user != null && admin == null) {
            if (user.getUserstatus() == 1) {
                return new Message("PASS", String.valueOf(user.getUserid()));
            }
            else {
                return new Message("FAIL", "user disabled by admin");
            }
        }
        else if (admin != null && user == null) {
            return new Message("PASS", "admin");
        }
        else {
            return new Message("FAIL", "wrong username or password");
        }
    }


    @Override
    public Message register_newuser(String username, String useremail, String userpwd) {
        try {
            if (username.trim().equals("") || useremail.trim().equals("") || userpwd.trim().equals("")) {
                return new Message("ERROR", "key attribute missing");
            }
            User phantom = userDao.findUserByUsername(username);
            Admin attack = adminDao.findAdminByAdminname(username);
            if (phantom != null || attack != null) {
                return new Message("ERROR", "conflict with existing user");
            }
            userDao.createNewUser(username,useremail,userpwd);
        }catch (Exception excp) {
            return new Message("ERROR", excp.getMessage());
        }
        User user = userDao.findUserByUsernameAndUserpwd(username, userpwd);
        if (user != null) {
            return new Message("SUCCESS", String.valueOf(user.getUserid()));
        }
        return new Message("ERROR", "something went wrong");
    }

    @Override
    public Message modify_user(String username, String useremail, String userpwd) {
        return new Message("FAIL", "still in development");
    }

}
