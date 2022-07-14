package com.demo.Group;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class GroupService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private GroupRepository groupRepository;

    private String success = "{\"message:\":\"success\"}";
    private String failure = "{\"message:\":\"failure\"}";

    /**
     * 
     * @param id The id to retreieve
     * @return the Group associated with the given id
     */
    public Group getGroupById(int id) {
        if(id < 0){
            return null;
        }
        return groupRepository.findById(id);
    }
    /**
     * 
     * @param groupName the name of the group to retrieve
     * @return the group associated with the given name
     */
    public Group getGroupByName(String groupName){
        if(!validateParameter(groupName)){
            return null;
        }
        return groupRepository.findByGroupName(groupName);
    }

    /**
     * 
     * @param userName the name of the user to query by
     * @return a List of all the groups a user is in
     */
    public List<Group> getGroupsForUser(String userName){
        if(!validateParameter(userName)){
            return null;
        }
        String sql = "SELECT cy_groups.id, cy_groups.group_name FROM cy_groups JOIN user_group_association ON cy_groups.id = "
				+ "user_group_association.group_id WHERE user_group_association.user_name = "
				+ "'" + userName + "'";  
		
		List<Group> query = this.jdbcTemplate.query(sql, new RowMapper<Group>() {
			public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
				Group group = new Group();
				group.setGroupId(rs.getInt("id"));
				group.setGroupName(rs.getString("group_name"));
				return group;
			}
			
		});
		return query;
    }
    /**
     * 
     * @return A list of all groups in the repository
     */
    public List<Group> getAllGroups(){
        return groupRepository.findAll();
    }

    /**
     * 
     * @param newGroup
     * @return A string of the staus of the group insertion. Will be either falure or success
     */
    public String addGroup(Group newGroup){
        if(!validateParameter(newGroup)){
			return failure;
		}
		groupRepository.save(newGroup);
		return success;
    }

    /**
     * 
     * @param groupId the group_id to update
     * @param newGroup the data to update for the given group_id
     * @return
     */
    public Group updateGroup(int groupId, Group newGroup){
        if(!validateParameter(newGroup) || groupId < 0){
            return null;
        }
        Group group = groupRepository.findById(groupId);
		if(group == null){
			return null;
		}
		groupRepository.save(newGroup);
		return groupRepository.findById(groupId);
    }
    /**
     * 
     * @param groupId the group_id to delete in the repository
     * @return the status of the deletion(success, failure)
     */
    public String deleteGroup(int groupId){
        if(groupId < 0){
            return failure;
        }
        groupRepository.deleteById(groupId);
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
