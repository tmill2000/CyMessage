// package com.demo;

// import static org.junit.Assert.assertEquals;
// import static org.mockito.Mockito.when;

// import org.junit.Before;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import org.springframework.boot.test.context.SpringBootTest;

// import com.demo.UserAssociation.UserAssociation;
// import com.demo.UserAssociation.UserAssociationController;
// import com.demo.UserAssociation.UserAssociationRepository;
// import com.demo.UserAssociation.UserAssociationService;

// @SpringBootTest
// public class TestingUserAssociation {

// 	@InjectMocks
// 	UserAssociationController userAssociationController;
	
// 	@Mock
// 	UserAssociationService userAssociationService;
	
// 	@Before
//     public void init(){
//         MockitoAnnotations.openMocks(this);
//     }
	
// 	/*
// 	 * Test adding user association
// 	 */
	
// 	@Test
// 	public void addUserAssociationTest() {
// 		when(userAssociationService.addUserAssociation(new UserAssociation(1, "RickyBobby", 1, "user")))
// 			.thenReturn(new UserAssociation(1, "RickyBobby", 1, "user"));
		
// 		String result = userAssociationController.addUserAssociation(
// 				new UserAssociation(1, "RickyBobby", 1, "user"));
		
// 		assertEquals(result, "{\"message:\":\"success\"}");
// 	}
	
// 	@Test
// 	public void getUserAssociationByIDTest() {
// 		when(userAssociationService.findById(1)).thenReturn(new UserAssociation(1, "RickyBobby", 1, "user"));
		
// 		UserAssociation userAssociation = userAssociationController.getUserAssociationByID(1);
		
// 		assertEquals(userAssociation.getId(), 1);
// 		assertEquals(userAssociation.getUserName(), "RickyBobby");
// 		assertEquals(userAssociation.getGroupId(), 1);
// 		assertEquals(userAssociation.getRole(), "user");
// 	}
	
// 	@Test 
// 	public void addUpdateUserAssociationTest() {
// 		when(userAssociationService.save(new UserAssociation(1, "RickyBobby", 1, "user")))
// 			.thenReturn(new UserAssociation(1, "RickyBobby", 1, "user"));
		
// 		when(userAssociationService.save(new UserAssociation(1, "MicahG", 1,  "admin")))
// 		.thenReturn(new UserAssociation(1, "MicahG", 1, "admin"));
		
// 		when(userAssociationService.findById(1)).thenReturn(new UserAssociation(1, "MicahG", 1, "admin"));
		
// 		String result = userAssociationController.addUserAssociation(
// 			new UserAssociation(1, "RickyBobby", 1, "user"));
		
// 		UserAssociation userAssociation = userAssociationController
// 				.updateUser(1, new UserAssociation(1, "MicahG", 1, "admin"));
		
// 		assertEquals(userAssociation.getId(), 1);
// 		assertEquals(userAssociation.getUserName(), "MicahG");
// 		assertEquals(userAssociation.getGroupId(), 1);
// 		assertEquals(userAssociation.getRole(), "admin");
// 	}
	
// 	@Test
// 	public void addRemoveUserAssociationTest() {
// 		when(userAssociationService.save(new UserAssociation(1, "RickyBobby", 1, "user")))
// 		.thenReturn(new UserAssociation(1, "RickyBobby", 1, "user"));
		
// 		String result = userAssociationController.addUserAssociation(
// 				new UserAssociation(1, "RickyBobby", 1, "user"));
		
// 		assertEquals(result, "{\"message:\":\"success\"}");
		
// 		String result2 = userAssociationController.removeUser(1);
		
// 		assertEquals(result2, "{\"message:\":\"success\"}");
		
// 	}
	
	
// }
