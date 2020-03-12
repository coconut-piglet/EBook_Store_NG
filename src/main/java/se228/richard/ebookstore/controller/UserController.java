package se228.richard.ebookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se228.richard.ebookstore.entity.Message;
import se228.richard.ebookstore.entity.User;
import se228.richard.ebookstore.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/checkusername",method = RequestMethod.GET)
    public Message checkUsername(@RequestParam(value = "username",required = true) String username) {
        System.out.println(username);
        return userService.check_availability(username);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Message verifyUser(@RequestBody User user) {
        String username = user.getUsername();
        String userpwd = user.getUserpwd();
        return userService.verify_credential(username, userpwd);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Message registerNewUser(@RequestBody User newuser) {
        String username = newuser.getUsername();
        String useremail = newuser.getUseremail();
        String userpwd = newuser.getUserpwd();
        return userService.register_newuser(username, useremail, userpwd);
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public Message modifyUser(@RequestBody User user) {
        int userid = user.getUserid();
        String username = user.getUsername();
        String useremail = user.getUseremail();
        String userpwd = user.getUserpwd();
        int userstatus = user.getUserstatus();
        return new Message("SUCCESS", "");
    }

}
