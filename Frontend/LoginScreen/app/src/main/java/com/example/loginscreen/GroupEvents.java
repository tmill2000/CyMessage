package com.example.loginscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.loginscreen.api.SlimCallback;
import com.example.loginscreen.model.Group;
import com.example.loginscreen.model.GroupEvent;
import static com.example.loginscreen.api.ApiClientFactory.GetGroupEventApi;
import static com.example.loginscreen.api.ApiClientFactory.GetGroupApi;

import java.util.Date;
import java.util.List;

public class GroupEvents extends AppCompatActivity {

    public void groupEvents(GroupEvent groupEvents, Group groupName) {
        LinearLayout linearLayout = findViewById(R.id.linearLayoutEvents);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(800, 350);
        TextView toAdd = new TextView(this);
        String body = groupEvents.getEventBody();
        Date date = groupEvents.getTimestamp();
        toAdd.setText("Group: " + groupName.getGroupName() + "\nTitle: " + body + "\nDate: " + date.toString());
        toAdd.setLayoutParams(layoutParams);
        linearLayout.addView(toAdd);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_events);

        Button addNewGroupEventsButton = findViewById(R.id.addNewGroupEventsButton);
        Button backGroupEventsButton = findViewById(R.id.backGroupEventsButton);
        Button manageGroupButton = findViewById(R.id.manageGroupButton);

        Intent getGroupIntent = getIntent();
        Bundle userInfo = getGroupIntent.getExtras();

        GetGroupEventApi().getAllGroupEvents().enqueue(new SlimCallback<List<GroupEvent>>(response1 -> {
            for(int i = 0; i < response1.size(); i++) {
                Group group = new Group();
                int j = i;
                GetGroupApi().getGroupByID(response1.get(i).getGroupId()).enqueue(new SlimCallback<Group>(response2 -> {
                    group.setGroupName(response2.getGroupName());
                    groupEvents(response1.get(j), group);
                }));
            }
        }));

        backGroupEventsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homepageIntent = new Intent(GroupEvents.this, Homepage.class);
                homepageIntent.putExtras(userInfo);
                startActivity(homepageIntent);
            }
        });

        addNewGroupEventsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), CreateGroupEvents.class));
            }
        });

        manageGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), ManageGroups.class));
            }
        });
    }
}