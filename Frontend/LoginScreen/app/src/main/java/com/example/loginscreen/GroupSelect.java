package com.example.loginscreen;

import static com.example.loginscreen.api.ApiClientFactory.GetGroupApi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.loginscreen.api.SlimCallback;
import static com.example.loginscreen.api.ApiClientFactory.GetUserAssociationApi;

import com.example.loginscreen.model.Group;
import com.example.loginscreen.model.UserAssociation;

import java.util.List;

public class GroupSelect extends AppCompatActivity {


    /**
     * (Blake's comment)
     * Gets list of groups user is in and dynamically creates buttons to represent each group
     * @param groups groups user is in
     * @param userInfo contains user information
     */
    public void groupsForUser(List<Group> groups, Bundle userInfo) {
        LinearLayout linearLayout = findViewById(R.id.linearLayout);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(800, 150);

        for(int i = 0; i < groups.size(); i++) {
            Button buttonTest = new Button(this);
            buttonTest.setText(groups.get(i).getGroupName());
            buttonTest.setId(i);
            buttonTest.setLayoutParams(layoutParams);
            linearLayout.addView(buttonTest);

            int groupId = groups.get(i).getGroupId();
            String groupName = groups.get(i).getGroupName();

            buttonTest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    userInfo.putInt("GROUP_ID", groupId);
                    userInfo.putString("GROUP_NAME", groupName);
                    Intent messagesIntent = new Intent(GroupSelect.this, Messages.class);
                    messagesIntent.putExtras(userInfo);
                    startActivity(messagesIntent);
                }
            });
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_select);

        Intent getGroupIntent = getIntent();
        Bundle userInfo = getGroupIntent.getExtras();

        Button backButton = findViewById(R.id.backHomepage);

        GetGroupApi().getGroupsForUser(userInfo.getString("USERNAME")).enqueue(new SlimCallback<List<Group>>(response -> {
            groupsForUser(response, userInfo);
        }, "CustomTag"));


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homepageIntent = new Intent(GroupSelect.this, Homepage.class);
                homepageIntent.putExtras(userInfo);
                startActivity(homepageIntent);
            }
        });
    }


}