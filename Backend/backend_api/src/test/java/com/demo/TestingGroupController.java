package com.demo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.demo.Group.Group;
import com.demo.Group.GroupController;
import com.demo.Group.GroupService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class TestingGroupController {

        @InjectMocks
        GroupController groupController;
    
        @Mock
        GroupService groupService;

        @Before
        public void init(){
            MockitoAnnotations.openMocks(this);
        }

        @Test
        public void getGroupTest(){
            Group g = new Group();
            g.setGroupId(1);
            g.setGroupName("TestGroup");
            when(groupService.getGroupById(1)).thenReturn(g);

            Group group = groupController.getGroupByID(1);

            assertEquals(1, group.getGroupId());
            assertEquals("TestGroup", group.getGroupName());
        }

        @Test
        public void getAllGroupTest(){
            Group g = new Group();
            g.setGroupId(1);
            g.setGroupName("TestGroup");

            Group g2 = new Group();
            g2.setGroupId(2);
            g2.setGroupName("TestGroup2");

            Group g3 = new Group();
            g3.setGroupId(1);
            g3.setGroupName("TestGroup3");

            List<Group> groups = new ArrayList<>();
            groups.add(g);
            groups.add(g2);
            groups.add(g3);
            when(groupService.getAllGroups()).thenReturn(groups);

            List<Group> allGroups = groupController.getAllGroups();

            assertEquals(g, allGroups.get(0));
            assertEquals(g2, allGroups.get(1));
            assertEquals(g3, allGroups.get(2));
        }


    
}

