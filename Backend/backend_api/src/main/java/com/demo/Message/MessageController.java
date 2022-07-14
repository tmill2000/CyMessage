package com.demo.Message;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

	@Autowired
	private MessageService messageService;


	public MessageController() {

	}

	@GetMapping(value ="/getMessagesByGroupId")
	public List<Message> getMessagesByGroupId(@RequestParam("group_id") int groupId){
		return messageService.getMessagesByGroupId(groupId);
	}

	@GetMapping(value = "/getMessage")
	public List<Message> getMessage(@RequestParam("group_id") int groupId, @RequestParam("sent_by") String sentBy) {
		return messageService.getUserMessages(groupId, sentBy);
	}

	@PostMapping(value = "/addMessage")
	public String addMessage(@RequestBody Message newMessage) {
		return messageService.addMessage(newMessage);
	}

	@PutMapping(value = "/updateMessage")
	public Message updateMessage(@RequestParam int id){
		return messageService.updateMessage(id);
	}

	@DeleteMapping(value="/deleteMessage")
	public String deleteMessage(@RequestParam int id){
		return messageService.deleteMessage(id);
	}

}
