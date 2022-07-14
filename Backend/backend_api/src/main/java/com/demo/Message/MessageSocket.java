package com.demo.Message;

import java.io.IOException;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
/**
 * User connects to a specific group and then retrieved all messages from the database for that group. 
 * If the user sends a message the message goes to anyone in a session with the same groupID
 */
@Controller
@ServerEndpoint(value = "/message/{group_id}/{user_name}")
public class MessageSocket {

	private static MessageService messageService;

	@Autowired
	public void setMessageService(MessageService service) {
		messageService = service;
	}

	private static Map<Session, String> sessionUsernameMap = new Hashtable<>();
	private static Map<String, Session> usernameSessionMap = new Hashtable<>();

	private static Map<Session, Integer> sessionGroupMap = new Hashtable<>();
	private static Map<Integer, Session> groupSessionMap = new Hashtable<>();

	private final Logger logger = LoggerFactory.getLogger(MessageSocket.class);

	@OnOpen
	public void onOpen(Session session, @PathParam("user_name") String userName, @PathParam("group_id") int groupId)
			throws IOException {
		logger.info(userName + " Entered into Open");

		sessionUsernameMap.put(session, userName);
		usernameSessionMap.put(userName, session);

		sessionGroupMap.put(session, groupId);
		groupSessionMap.put(groupId, session);

		List<Message> messages = messageService.getMessagesByGroupId(groupId);

		sendMessagesToUser(userName, messages);
	}

	@OnMessage
	public void onMessage(Session session, String message) throws IOException {

		logger.info("Entered into Message: Got Message:" + message);
		int groupId = sessionGroupMap.get(session);
		String userName = sessionUsernameMap.get(session);
		sendMessage(message, groupId, userName );

		Message m = new Message();
		m.setGroupID(groupId);
		m.setMessageBody(message);
		m.setTimestamp(new Date());
		m.setSentBy(userName);

		messageService.addMessage(m);
	}

	@OnClose
	public void onClose(Session session) throws IOException {
		logger.info("Entered into Close");

		String username = sessionUsernameMap.get(session);
		Integer groupId = sessionGroupMap.get(session);
		sessionUsernameMap.remove(session);
		usernameSessionMap.remove(username);

		groupSessionMap.remove(session);
		sessionGroupMap.remove(groupId);
	}

	@OnError
	public void onError(Session session, Throwable throwable) {
		logger.info("Entered into Error");
		throwable.printStackTrace();
	}

	private void sendMessagesToUser(String userName, List<Message> messages) {
		try {
			for (Message message : messages) {
				usernameSessionMap.get(userName).getBasicRemote().sendText(message.toString());
			}

		} catch (IOException e) {
			logger.info("Exception: " + e.getMessage().toString());
			e.printStackTrace();
		}

	}

	private void sendMessage(String message, int groupId, String sentBy) {
		sessionUsernameMap.forEach((session, username) -> {
			try {
				if (sessionGroupMap.get(session) == groupId) {
					Message m = new Message();
					m.setGroupID(groupId);
					m.setMessageBody(message);
					m.setSentBy(sentBy);
					m.setTimestamp(new Date());
					session.getBasicRemote().sendText(m.toString());
				}
			} catch (IOException e) {
				logger.info("Exception: " + e.getMessage().toString());
				e.printStackTrace();
			}

		});
	}
}
