package com.example.loginscreen;

import static com.example.loginscreen.api.ApiClientFactory.GetGroupApi;
import static com.example.loginscreen.api.ApiClientFactory.GetGroupEventApi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.loginscreen.api.SlimCallback;
import com.example.loginscreen.model.Group;
import com.example.loginscreen.model.GroupEvent;

import java.util.Date;

public class CreateGroupEvents extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group_events);

        Button addEventButton = findViewById(R.id.addEventButton);
        Button backCGEButton = findViewById(R.id.backCGEButton);
        EditText groupEventTitle = findViewById(R.id.titleCGETextEdit);
        EditText groupEventDescription = findViewById(R.id.descriptionCGETextEdit);
        EditText groupEventGroup = findViewById(R.id.groupCGETextEdit);

        addEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GetGroupApi().getGroupByName(groupEventGroup.getText().toString()).enqueue(new SlimCallback<Group>(response1 -> {
                    GroupEvent groupEvent = new GroupEvent();
                    groupEvent.setGroupId(response1.getGroupId());
                    String title = groupEventTitle.getText().toString();
                    String body = groupEventDescription.getText().toString();
                    groupEvent.setEventBody(title + "\n" + body);
                    groupEvent.setTimestamp(null);
                    GetGroupEventApi().addGroupEvent(groupEvent).enqueue(new SlimCallback<GroupEvent>(response2 -> {
                        groupEventTitle.setText("");
                        groupEventDescription.setText("");
                        groupEventGroup.setText("");
                    }));
                }));
            }
        });

        backCGEButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), GroupEvents.class));
            }
        });
    }
}