package com.demo.GroupEvents;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GroupEventController {

    @Autowired
    private GroupEventService groupEventService;

    @GetMapping(path = "/getAllGroupEvents")
    public List<GroupEvent> getAllGroupEvents() {
        return groupEventService.getAllGroupEvents();
    }

    @GetMapping(path = "/getGroupById")
    public GroupEvent getGroupById(@RequestParam int id){
        return groupEventService.getGroupById(id);
    }

    @PostMapping(path = "/addGroupEvent")
    public String addGroupEvent(@RequestBody GroupEvent gEvent) {
        return groupEventService.addGroupEvent(gEvent);
    }

    @PutMapping(path = "/updateGroupEvent")
    public GroupEvent updateGroupEvent(@RequestParam int id, @RequestBody GroupEvent newGroupEvent){
        return groupEventService.updateGroupEvent(id, newGroupEvent);
    }

    @DeleteMapping( path="/deleteGroupEvent")
	String deleteGroup(@RequestParam int id){
		return groupEventService.deleteGroup(id);
	}
}
