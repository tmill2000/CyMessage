package com.demo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CyUserService {
	
    private String success = "{\"message:\":\"success\"}";	
	private String failure = "{\"message:\":\"failure\"}";
    private String invalidRequest = "{\"message:\":\"User Already Exists\"}";

    @Autowired
    private CyUserRepository userRepository;

    /**
     * 
     * @param userName The entered user name to validate
     * @param password The entered password to validate
     * @return An instance of the validated user, or null if unable to validate
     */
    public CyUser validateUser(String userName, String password){
        if(!validateParameter(userName) || !validateParameter(password)){
            return null;
        }
        CyUser u =  userRepository.findByUserName(userName);
        if(u.getPassword().equals(password)){
            u.setPassword("validated");
        }else{
            u.setPassword("unvalidated");
            u.setEmail("restricted");
        }
        return u;
    }
    
    /**
     * 
     * @param newUser The user instance to create an account for
     * @return A message designating success or failure for the operation
     */
    public String createNewAccount(CyUser newUser){
        if(!validateParameter(newUser)){
            return failure;
        }
        CyUser user = userRepository.findByUserName(newUser.getUserName());

        if(user == null || user.getUserName() == null){
            userRepository.save(newUser);
            return success;
        }
        return invalidRequest;
    }
    
    
    /**
     * 
     * @param userName The username to query the database for
     * @return The queried user or null if not found
     */
    public CyUser getUserByUserName(String userName){
        if(!validateParameter(userName)){
            return null;
        }
        CyUser u = userRepository.findByUserName(userName);
        if(u == null){
            return new CyUser();
        }
        return u;
    }

    /**
     * 
     * @param user The given user to update
     * @param id The id of the user to update
     * @return The updated user instance or null if not found
     */
    public CyUser updateUser(CyUser user, int id){
        if(!validateParameter(user) || id < 0){
            return null;
        }

        CyUser u = userRepository.findById(id);

        if(!validateParameter(u)){
            return null;
        }
        userRepository.save(user);
        return userRepository.findById(id);
    }
    
    /**
     * 
     * @param id The id of the user account to delete
     * @return A message designating success or failure of the deletion
     */
    public String deleteAccount(int id){
        if(id < 0){
            return failure;
        }
        userRepository.deleteById(id);
        return success;
    }
    

    /**
     * 
     * @param obj The parameter to validate
     * @return The boolean designating validation status
     */
    public boolean validateParameter(Object obj){
        if(obj == null){
            return false;
        }
        if(obj instanceof String ){
            if(obj.toString().length() == 0){
                return false;
            }
        }
        return true;
    }
    
}
