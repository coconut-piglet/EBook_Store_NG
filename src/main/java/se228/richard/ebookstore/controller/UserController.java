package se228.richard.ebookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import se228.richard.ebookstore.entity.Message;
import se228.richard.ebookstore.entity.User;
import se228.richard.ebookstore.service.UserService;

import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@Scope("singleton")
@RequestMapping("/api")
public class UserController {

    //Legacy Code
    //@Autowired
    //private UserService userService;

    @Autowired
    WebApplicationContext applicationContext;

    @RequestMapping(value = "/checkusername",method = RequestMethod.GET)
    public Message checkUsername(@RequestParam(value = "username",required = true) String username) {
        UserService userService = applicationContext.getBean(UserService.class);
        // Print the service created and myself
        System.out.println("本次使用的service：" + userService);
        System.out.println("本次使用的controller：" + this);
        return userService.check_availability(username);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Message verifyUser(@RequestBody User user) {
        UserService userService = applicationContext.getBean(UserService.class);
        // Print the service created and myself
        System.out.println("本次使用的service：" + userService);
        System.out.println("本次使用的controller：" + this);
        String username = user.getUsername();
        String userpwd = user.getUserpwd();
        return userService.verify_credential(username, userpwd);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Message registerNewUser(@RequestBody User newuser) {
        UserService userService = applicationContext.getBean(UserService.class);
        // Print the service created and myself
        System.out.println("本次使用的service：" + userService);
        System.out.println("本次使用的controller：" + this);
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
