package com.example.loginscreen;

import static com.example.loginscreen.api.ApiClientFactory.GetMessageApi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;

import com.example.loginscreen.api.SlimCallback;
import com.example.loginscreen.model.Message;
import com.google.gson.Gson;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;


public class Messages extends AppCompatActivity {


    private TextView allMessages;

    private WebSocketClient cc;

    private Button backGroupSelect;
    private Button settingsButton;
    private Button sendMessageButton;
    private EditText sentMessage;
    private TextView groupName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        backGroupSelect = findViewById(R.id.backgroupSelect);
        settingsButton = findViewById(R.id.settingsButton);
        sendMessageButton = findViewById(R.id.sendButton);
        sentMessage = findViewById(R.id.enterMessageTextEdit);
        groupName = findViewById(R.id.groupTextEdit);
        allMessages = findViewById(R.id.messagesTextView);
        Intent getMessagesIntent = getIntent();
        Bundle userInfo = getMessagesIntent.getExtras();

        groupName.setText(userInfo.getString("GROUP_NAME"));

        Draft[] drafts = {
                new Draft_6455()
        };

        String w = "ws://coms-309-055.cs.iastate.edu:8080/message/" + userInfo.getInt("GROUP_ID") + "/" + userInfo.getString("USERNAME");

        try {
            Log.d("Socket:", "Trying socket");
            cc = new WebSocketClient(new URI(w), (Draft) drafts[0]) {
                @Override
                public void onMessage(String message) {
                    Gson g = new Gson();
                    Message m = g.fromJson(message, Message.class);
                    Log.d("", "run() returned: ID: " + m.getMessageId()+ ": "+ m.getMessageBody());


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String date;
                                if(m.getMessageId() == 0){
                                    Date d = new Date();
                                    DateFormat df = new SimpleDateFormat("yyyy-M-dd HH:mm:ss");
                                    date = df.format(d);
                                }else{
                                    date = m.getTimestamp();
                                }
                                allMessages.append("\n\n" + date + "\n" + m.getSentBy() + ": " + m.getMessageBody());
                            }
                        });



                }

                @Override
                public void onOpen(ServerHandshake handshake) {
                    Log.d("OPEN", "run() returned: " + "is connecting");
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    Log.d("CLOSE", "onClose() returned: " + reason);
                }

                @Override
                public void onError(Exception e) {
                    Log.d("New Exception:", e.toString());
                }
            };
        } catch (URISyntaxException e) {
            Log.d("Exception:", e.getMessage().toString());
            e.printStackTrace();
        }
        cc.connect();


        backGroupSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent groupSelectIntent = new Intent(Messages.this, GroupSelect.class);
                groupSelectIntent.putExtras(userInfo);
                startActivity(groupSelectIntent);
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent SettingsIntent = new Intent(Messages.this, Settings.class);
                SettingsIntent.putExtras(userInfo);
                startActivity(SettingsIntent);
            }
        });

        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!sentMessage.getText().toString().equals("")) {
                    Message m = new Message();
                    m.setMessageBody(sentMessage.getText().toString());
                    m.setGroupID(userInfo.getInt("GROUP_ID"));
                    m.setSentBy(userInfo.getString("USERNAME"));
                    cc.send(m.getMessageBody());
                }
                sentMessage.setText("");
            }
        });

    }


}