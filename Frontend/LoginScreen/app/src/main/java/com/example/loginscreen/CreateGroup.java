package com.example.loginscreen;

import static com.example.loginscreen.api.ApiClientFactory.GetUserApi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginscreen.api.SlimCallback;
import com.example.loginscreen.model.Group;
import com.example.loginscreen.model.User;
import com.example.loginscreen.model.UserAssociation;

import static com.example.loginscreen.api.ApiClientFactory.GetGroupApi;
import static com.example.loginscreen.api.ApiClientFactory.GetUserAssociationApi;

import java.util.List;

public class CreateGroup extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        Intent getAddGroupIntent = getIntent();
        Bundle extras = getAddGroupIntent.getExtras();

        Button createGroupHomepage = findViewById(R.id.createGroupHomepage);
        Button groupCreateButton = findViewById(R.id.groupCreateButton);
        Button addUserButton = findViewById(R.id.createGroupButton);
        EditText createGroup = findViewById(R.id.groupnameTextEdit);
        EditText userAdd = findViewById(R.id.searchuserTextEdit);

        groupCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Group group = new Group();
                group.setGroupName(createGroup.getText().toString());
                GetGroupApi().addGroup(group).enqueue(new SlimCallback<Group>(response -> {
                },"CustomTag"));


            }

        });

        createGroupHomepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homepageIntent = new Intent(CreateGroup.this, Homepage.class);
                homepageIntent.putExtras(extras);
                startActivity(homepageIntent);
            }
        });

        addUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GetUserApi().getUser(userAdd.getText().toString()).enqueue(new SlimCallback<User>(response -> {
                    if (response.getUserId() == 0) {
                        Toast.makeText(CreateGroup.this, "Username does not exist", Toast.LENGTH_SHORT).show();
                        userAdd.setText("");
                    }
                    else {
                        GetGroupApi().getGroupByName(createGroup.getText().toString()).enqueue(new SlimCallback<Group>(response2 -> {
                            UserAssociation user = new UserAssociation();
                            user.setGroupId(response2.getGroupId());
                            user.setUserName(userAdd.getText().toString());
                            if(extras.getString("USERNAME").equals(userAdd.getText().toString())) {
                                user.setRole("owner");
                            }
                            else {
                                user.setRole("user");
                            }
                            GetUserAssociationApi().addUserAssociation(user).enqueue(new SlimCallback<UserAssociation>(response3 -> {
                            }));
                        }));
                    }
                }));
            }
        });
    }
}