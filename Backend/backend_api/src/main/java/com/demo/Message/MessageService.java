package com.demo.Message;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;


@Service
public class MessageService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private MessageRepository messageRepository;

    private String success = "{\"message:\":\"success\"}";
    private String failure = "{\"message:\":\"failure\"}";

    /**
     * 
     * @param groupId group_id of the group to retrieve messages for
     * @return a list of messages that are associated to the given group_id
     */
    public List<Message> getMessagesByGroupId(int groupId) {
        if (groupId < 0) {
            return null;
        }
        String sql = "SELECT DISTINCT user.user_name, messages.id, messages.group_id, messages.message_body, messages.timestamp from messages left join user on  user.user_name = messages.sent_by WHERE group_id = "
                + groupId + " ORDER BY messages.timestamp ASC;";
        List<Message> queryResult = this.jdbcTemplate.query(sql, new RowMapper<Message>() {
            public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
                Message userMessage = new Message();
                userMessage.setMessageId(rs.getInt("id"));
                userMessage.setGroupID(rs.getInt("group_id"));
                userMessage.setMessageBody(rs.getString("message_body"));
                userMessage.setTimestamp(rs.getTimestamp("timestamp"));
                userMessage.setSentBy(rs.getString("user_name"));
                return userMessage;
            }
        });
        return queryResult;
    }

    /**
     * 
     * @param groupId group_id to query
     * @param sentBy  user_name of user to get messages for
     * @return a list of messages sent by the given user_name and within the given
     *         group
     */
    public List<Message> getUserMessages(int groupId, String sentBy) {
        if (groupId < 0 || !validateParameter(sentBy)) {
            return null;
        }

        String sql = "SELECT user.user_name, messages.id, messages.group_id, messages.message_body, messages.timestamp from messages join user on  user.user_name = messages.sent_by WHERE group_id = "
                + groupId + " AND sent_by = " + "'" + sentBy + "'" + " ORDER BY messages.id ASC";
        List<Message> queryResult = this.jdbcTemplate.query(sql, new RowMapper<Message>() {
            public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
                Message userMessage = new Message();
                userMessage.setGroupID(rs.getInt("group_id"));
                userMessage.setMessageBody(rs.getString("message_body"));
                userMessage.setTimestamp(rs.getDate("timestamp"));
                userMessage.setSentBy(rs.getString("user_name"));
                return userMessage;
            }
        });
        return queryResult;
    }

    /**
     * 
     * @param newMessage the message to be inserted into the repository
     * @return the status of the message insertion(success, failure)
     */
    public String addMessage(Message newMessage) {
        if (!validateParameter(newMessage)) {
            return failure;
        }
        newMessage.setTimestamp(new Date());
        messageRepository.save(newMessage);
        return success;
    }

    /**
     * 
     * @param id the id of the message object to update
     * @return the updated message data after upsert
     */
    public Message updateMessage(int id) {
        if (id < 0) {
            return null;
        }
        Message message = messageRepository.findById(id);
        if (message == null) {
            return null;
        }
        messageRepository.save(message);
        return messageRepository.findById(id);
    }
    /**
     * 
     * @param id the id of the message to delete
     * @return the status of the message deletion(success, failure)
     */
    public String deleteMessage(int id) {
        if (id < 0) {
            return failure;
        }
        messageRepository.deleteById(id);
		return success;
    }

    /**
     * 
     * @param obj The parameter to validate
     * @return The boolean designating validation status
     */
    public boolean validateParameter(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof String) {
            if (obj.toString().length() == 0) {
                return false;
            }
        }
        return true;
    }
}
