package com.demo.Group;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GroupController {

	@Autowired
	private GroupService groupService;


	public GroupController() {
		
	}
	
	@GetMapping(value ="/getGroupByID")
	public Group getGroupByID(@RequestParam("group_id") int groupId){
		return groupService.getGroupById(groupId);
	}

	@GetMapping(value = "/getGroupByName")
	public Group getGroupByName(@RequestParam("group_name") String groupName){
		return groupService.getGroupByName(groupName);
	}
	
	@GetMapping(value ="/getGroupsForUser")
	public List<Group> getGroupsForUser(@RequestParam("user_name") String userName){
		return groupService.getGroupsForUser(userName);
	}
	
	@GetMapping(value="/getAllGroups")
	public List<Group> getAllGroups(){
		return groupService.getAllGroups();
	}
	
	@PostMapping(value="/addGroup")
	public String addGroup(@RequestBody Group newGroup) {
		return groupService.addGroup(newGroup);
	}
	@PutMapping(value = "/updateGroup")
	public Group updateGroup(@RequestParam int groupId, @RequestBody Group newGroup){
		return groupService.updateGroup(groupId, newGroup);
	}

	@DeleteMapping(value="/deleteGroup")
	public String deleteGroup(@RequestParam int groupId){
		return groupService.deleteGroup(groupId);
	}
}
