package com.demo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import com.demo.User.CyUser;
import com.demo.User.CyUserController;
import com.demo.User.CyUserService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class TestingCyUserController {

        @InjectMocks
        CyUserController cyUserController;
    

        @Mock
        CyUserService userService;





        @Before
        public void init(){
            MockitoAnnotations.openMocks(this);
        }

        @Test
        public void getUserTest(){
            when(userService.getUserByUserName("RickyBobby")).thenReturn(new CyUser(1, "RickyBobby", "password", "shakeandbake@gmail.com"));

            CyUser user = cyUserController.getUser("RickyBobby");

            assertEquals("RickyBobby", user.getUserName());
            assertEquals(1, user.getUserId());
            assertEquals("shakeandbake@gmail.com", user.getEmail());
            assertEquals("password", user.getPassword());
        }

        @Test
        public void getUserTest2(){
            CyUser user = cyUserController.getUser(null);

            assertEquals(null, user);
        }
 
        //Test for when a user already exists
        @Test
        public void postUserTest(){
            when(userService.getUserByUserName("RickyBobby")).thenReturn(new CyUser(1, "RickyBobby", "password", "shakeandbake@gmail.com"));
            when(userService.createNewAccount(new CyUser(1, "RickyBobby", "password","shakeandbake@gmail.com"))).thenReturn("{\"message:\":\"User Already Exists\"}");

            String result = cyUserController.addUser(new CyUser(1, "RickyBobby", "password","shakeandbake@gmail.com"));
            assertEquals(result, "{\"message:\":\"User Already Exists\"}");

        }

           //Test for when a user doesn't exist
           @Test
           public void postUserTest2(){
               when(userService.getUserByUserName("RickyBobby")).thenReturn(null);
               when(userService.createNewAccount(new CyUser(1, "RickyBobby", "password","shakeandbake@gmail.com"))).thenReturn("{\"message:\":\"success\"}");
   
               String result = cyUserController.addUser(new CyUser(1, "RickyBobby", "password","shakeandbake@gmail.com"));
               assertEquals(result, "{\"message:\":\"success\"}");
           }
    
}
