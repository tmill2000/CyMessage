package com.demo.UserAssociation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.Group.Group;
import com.demo.User.CyUser;

@RestController
public class UserAssociationController {

	@Autowired
	private UserAssociationService userAssociationService;


	public UserAssociationController() {
		
	}
	
	@GetMapping(value = "/getUserAssociation")
	public List<UserAssociation> getUserAssociation(@RequestParam("user_name") String userName) {
		return userAssociationService.getUserAssociation(userName);
	}
	
	@GetMapping(value = "/getUserAssociationByGroup")
	public List<UserAssociation> getUserAssociationByGroup(@RequestParam("group_id") int groupId) {
		return userAssociationService.getUserAssociationByGroup(groupId);
	}
	
	@GetMapping(value = "/getUserAssociationByID")
	public UserAssociation getUserAssociationByID(@RequestParam("id") int id) {
		return userAssociationService.getUserAssociationByID(id);
	}
	
	@GetMapping(value = "/getUsersByGroup")
	public List<CyUser> getUsersByGroup(@RequestParam("group_id") int groupId){
		return userAssociationService.getUsersByGroup(groupId);
	}
	
	@GetMapping(value = "/getGroupsByUser")
	public List<Group> getGroupsByUser(@RequestParam("user_name") String userName){
		return userAssociationService.getGroupsByUser(userName);
	}
	
	
	@PostMapping(value = "/addUserAssociation")
	public String addUserAssociation(@RequestBody UserAssociation newUserAssociation) {
		return userAssociationService.addUserAssociation(newUserAssociation);
	} 

	@PutMapping(value = "/updateUserAssociation")
    public UserAssociation updateUser(@RequestParam("user_id") int id, @RequestBody UserAssociation newUserAssc){
        return userAssociationService.updateUser(id, newUserAssc);
    }

	@DeleteMapping(value = "/removeUserAssociation")
    public String removeUser(@RequestParam("user_id") int userId) {
        return userAssociationService.removeUser(userId);
    }
	
}
