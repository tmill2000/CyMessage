package com.demo.GroupEvents;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class GroupEventService {
	
	@Autowired
    GroupEventRepository groupEventRepository;

    private String success = "{\"message:\":\"success\"}";	
	private String failure = "{\"message:\":\"failure\"}";	
	
	/**
	 * 
	 * @return A list containing all existing group events
	 */
    public List<GroupEvent> getAllGroupEvents() {
        return groupEventRepository.findAll(Sort.by(Sort.Direction.ASC, "timestamp"));
    }
    /**
     * 
     * @param id The id to query for a group
     * @return The found group with the given id
     */
    public GroupEvent getGroupById(int id){
        return groupEventRepository.findById(id);
    }
    
    /**
     * 
     * @param gEvent The new group event to add to the database
     * @return A message designating success or failure of the operation
     */
    public String addGroupEvent(GroupEvent gEvent) {
        if(gEvent == null){
            return failure;
        }
         gEvent.setTimestamp(new Date());
         groupEventRepository.save(gEvent);
         return success;
    }
    
    /**
     * 
     * @param id The id of the group event to update
     * @param newGroupEvent The updated group event to add to the given id
     * @return The updated group event
     */
    public GroupEvent updateGroupEvent(int id,GroupEvent newGroupEvent){
        GroupEvent groupEvent = groupEventRepository.findById(id);
        if(groupEvent == null){
            return null;
        }
        groupEventRepository.save(newGroupEvent);
        return groupEventRepository.findById(id);
    }
    
    /**
     * 
     * @param id The id of the group event to delete
     * @return A message designating success or failure of the operation
     */
	String deleteGroup(int id){
		groupEventRepository.deleteById(id);
		return success;
	}
}
