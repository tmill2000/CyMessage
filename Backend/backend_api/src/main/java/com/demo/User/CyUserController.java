package com.demo.User;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CyUserController {

    @Autowired
    private CyUserService userService;

    public CyUserController() {

    }

    @GetMapping(value = "/getUser")
    public CyUser getUser(@RequestParam("user_name") String userName) {
        return userService.getUserByUserName(userName);
    }

    @GetMapping(value = "/getUserValidate")
    public CyUser getUserValidate(@RequestParam("user_name") String userName, @RequestParam("password") String password) {
        return userService.validateUser(userName, password);
    }

    @PostMapping(value = "/addUser")
	public String addUser(@RequestBody CyUser newUser) {
        return userService.createNewAccount(newUser);
    }

    @PutMapping(value = "/updateUser")
    public CyUser updateUser(@RequestParam int id, @RequestBody CyUser user){
        return userService.updateUser(user, id);
    }

    @DeleteMapping(value = "/removeUser")
    public String removeUser(@RequestParam int userId) {
        return userService.deleteAccount(userId);
    }
}
