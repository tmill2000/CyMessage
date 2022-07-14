package com.example.loginscreen;


import com.example.loginscreen.model.Message;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.doNothing;


import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MessagingTests {

//    @Before
//    public void init(){
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void testAddMessage() {
//        Messages messages = mock(Messages.class);
//        Message message = mock(Message.class);
//        doNothing().when(messages).addMessage(message);
//        messages.addMessage(message);
//        verify(messages,times(1)).addMessage(message);
//    }
//
//    @Test
//    public void testTextChange() {
//        Messages messages = mock(Messages.class);
//        Message message = mock(Message.class);
//        when(message.getMessageBody()).thenReturn("test");
//        List<Message> messageList = new ArrayList<Message>();
//        messageList.add(message);
//        messages.getMessages(messageList);
//
//        System.out.println(message.getMessageBody());
//        assertEquals("test", message.getMessageBody());
//    }
//
//    // @Test
//    // public void testZeroCalls() {
//    //     Messages messages = mock(Messages.class);
//    //     Message message = mock(Message.class);
//    //     List<Message> messageList = new ArrayList<Message>();
//    //     messageList.add(message);
//    //     doNothing().when(messages).getMessages(messageList);
//    //     verify(messages,times(0)).getMessages(messageList);
//    // }
//
//    // @Test
//    // public void testOneCall() {
//    //     Messages messages = mock(Messages.class);
//    //     Message message = mock(Message.class);
//    //     List<Message> messageList = new ArrayList<Message>();
//    //     messageList.add(message);
//    //     doNothing().when(messages).getMessages(messageList);
//    //     messages.getMessages(messageList);
//    //     verify(messages,times(1)).getMessages(messageList);
//    // }

}
