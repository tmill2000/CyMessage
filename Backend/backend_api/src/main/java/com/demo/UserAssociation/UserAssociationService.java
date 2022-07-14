package com.demo.UserAssociation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.demo.Group.Group;
import com.demo.User.CyUser;

@Service
public class UserAssociationService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private UserAssociationRepository userAssociationRepository;

	private String success = "{\"message:\":\"success\"}";	
	private String failure = "{\"message:\":\"success\"}";	

	public UserAssociationService() {
		
	}
	
	/**
	 * 
	 * @param userName The name to query for user associations
	 * @return The list of user associations found for the given user name
	 */
	public List<UserAssociation> getUserAssociation(String userName) {
		String sql = "SELECT * FROM user_group_association WHERE user_name = " + "'" + userName + "'"+ ";";
		List<UserAssociation> query = this.jdbcTemplate.query(sql, new RowMapper<UserAssociation>(){
			public UserAssociation mapRow(ResultSet rs, int rowNum) throws SQLException {
				UserAssociation newAssociation = new UserAssociation();
				newAssociation.setId(rs.getInt("id"));
				newAssociation.setUserName(rs.getString("user_name"));
				newAssociation.setGroupId(rs.getInt("group_id"));
				newAssociation.setRole(rs.getString("role"));
				return newAssociation;
			}
			
		});
		return query;
	}
	
	/**
	 * 
	 * @param groupId The group id to query for all user associations related to that group
	 * @return The list of user associations found for the given group id
	 */
	public List<UserAssociation> getUserAssociationByGroup(int groupId) {
		String sql = "SELECT * FROM user_group_association WHERE group_id = " + "'" + groupId + "'"+ ";";
		List<UserAssociation> query = this.jdbcTemplate.query(sql, new RowMapper<UserAssociation>(){
			public UserAssociation mapRow(ResultSet rs, int rowNum) throws SQLException {
				UserAssociation newAssociation = new UserAssociation();
				newAssociation.setId(rs.getInt("id"));
				newAssociation.setUserName(rs.getString("user_name"));
				newAssociation.setGroupId(rs.getInt("group_id"));
				newAssociation.setRole(rs.getString("role"));
				return newAssociation;
			}
			
		});
		return query;
	}
	/**
	 * 
	 * @param id The id to query for a user association
	 * @return The single user association with the given id
	 */
	public UserAssociation getUserAssociationByID(int id) {
		return userAssociationRepository.findById(id);
	}
	
	/**
	 * 
	 * @param groupId The group id to query for all users within the group
	 * @return The list of users found in the given group
	 */
	public List<CyUser> getUsersByGroup(int groupId){
		String sql = "SELECT * FROM user WHERE user_name IN (SELECT user_name FROM user_group_association WHERE group_id = " + "'" + groupId + "'"+ ");";
		List<CyUser> query = this.jdbcTemplate.query(sql, new RowMapper<CyUser>(){
			public CyUser mapRow(ResultSet rs, int rowNum) throws SQLException {
				CyUser user = new CyUser();
	            user.setUserId(rs.getInt("id"));
	            user.setUserName( rs.getString("user_name"));
	            user.setPassword(rs.getString("password"));
	            user.setEmail(rs.getString("email"));
	            return user;
			}
			
		});
		return query;
	}
	/**
	 * 
	 * @param userName The user name to query all groups that user is a part of
	 * @return The list of groups the given user is a part of
	 */
	public List<Group> getGroupsByUser(String userName){
		String sql = "SELECT * FROM groups WHERE group_id IN (SELECT group_id FROM user_group_association WHERE user_name = " + "'" + userName + "'"+ ");";
		List<Group> query = this.jdbcTemplate.query(sql, new RowMapper<Group>(){
			public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
				Group group = new Group();
				group.setGroupId(rs.getInt("group_id"));
				group.setGroupName(rs.getString("group_name"));
				return group;
			}
			
		});
		return query;
	}
	
	/**
	 * 
	 * @param newUserAssociation The new user association to add to the database
	 * @return A message designating success or failure of the operation
	 */
	public String addUserAssociation(UserAssociation newUserAssociation) {
		if(newUserAssociation == null){
			return failure;
		}
		userAssociationRepository.save(newUserAssociation);
		return success;
	} 
	
	/**
	 * 
	 * @param id The id of the user association to update
	 * @param newUserAssc The user association to be assigned to the given id
	 * @return The updated user association
	 */
    public UserAssociation updateUser(int id, UserAssociation newUserAssc){
        UserAssociation userAssc = userAssociationRepository.findById(id);
        if(userAssc == null){
            return null;
        }
        userAssociationRepository.save(newUserAssc);
        return userAssociationRepository.findById(id);
    }
    /**
     * 
     * @param userId The id of the user association to remove
     * @return A message designating success or failure of the operation
     */
    public String removeUser(int userId) {
        userAssociationRepository.deleteById(userId);
        return success;
    }
	
}
