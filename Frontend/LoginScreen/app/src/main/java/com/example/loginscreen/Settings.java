package com.example.loginscreen;

import static com.example.loginscreen.api.ApiClientFactory.GetGroupApi;
import static com.example.loginscreen.api.ApiClientFactory.GetUserApi;
import static com.example.loginscreen.api.ApiClientFactory.GetUserAssociationApi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.loginscreen.api.SlimCallback;
import com.example.loginscreen.model.Group;
import com.example.loginscreen.model.User;
import com.example.loginscreen.model.UserAssociation;

import java.util.List;

public class Settings extends AppCompatActivity {

    public UserAssociation chooseCorrectGroup(List<UserAssociation> userGroups, Bundle userInfo) {
        for (int i = 0; i < userGroups.size(); i++) {
            if (userGroups.get(i).getGroupId() == userInfo.getInt("GROUP_ID")) {
                return userGroups.get(i);
            }
        }
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button backMessages = findViewById(R.id.backMessages);
        Button userRole = findViewById(R.id.userRoleButton);
        Button addToGroup = findViewById(R.id.inviteButton);
        Button removeFromGroup = findViewById(R.id.userRemoveButton);

        EditText userToInvite = findViewById(R.id.usernameInviteTextEdit);
        EditText userToUpdate = findViewById(R.id.usernameUpdateTextEdit);
        EditText userToRemove = findViewById(R.id.usernameRemoveTextEdit);

        RadioButton userButton = findViewById(R.id.userButton);
        RadioButton adminButton = findViewById(R.id.adminButton);

        Intent getMessagesIntent = getIntent();
        Bundle userInfo = getMessagesIntent.getExtras();

        backMessages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent messagesIntent = new Intent(Settings.this, Messages.class);
                messagesIntent.putExtras(userInfo);
                startActivity(messagesIntent);
            }
        });

        userRole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetUserApi().getUser(userToUpdate.getText().toString()).enqueue(new SlimCallback<User>(response -> {
                    if (response.getUserId() == 0) {
                        Toast.makeText(Settings.this, "Username does not exist", Toast.LENGTH_SHORT).show();
                        userToUpdate.setText("");
                    } else {
                        GetUserAssociationApi().getUserAssociation(userInfo.getString("USERNAME")).enqueue(new SlimCallback<List<UserAssociation>>(response4 -> {
                            if (chooseCorrectGroup(response4, userInfo).getRole().equals("owner")) {
                                GetUserAssociationApi().getUserAssociation(userToUpdate.getText().toString()).enqueue(new SlimCallback<List<UserAssociation>>(response2 -> {
                                    UserAssociation u = new UserAssociation();
                                    UserAssociation currentUser = chooseCorrectGroup(response2, userInfo);
                                    u.setGroupId(currentUser.getGroupId());
                                    u.setUserName(currentUser.getUserName());
                                    u.setId(currentUser.getId());
                                    if (userButton.isChecked()) {
                                        u.setRole("user");
                                        GetUserAssociationApi().updateUserAssociation(currentUser.getId(), u).enqueue(new SlimCallback<UserAssociation>(response3 -> {
                                        }));
                                    } else if (adminButton.isChecked()) {
                                        u.setRole("admin");
                                        GetUserAssociationApi().updateUserAssociation(currentUser.getId(), u).enqueue(new SlimCallback<UserAssociation>(response3 -> {
                                            System.out.println(response3.getRole());
                                        }));
                                    } else {
                                        Toast.makeText(Settings.this, "Please select a role", Toast.LENGTH_SHORT).show();
                                    }
                                }));
                            } else {
                                Toast.makeText(Settings.this, "You must be an owner to do this", Toast.LENGTH_SHORT).show();
                            }
                        }));
                    }
                }));
            }
        });

        addToGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetUserApi().getUser(userToInvite.getText().toString()).enqueue(new SlimCallback<User>(response -> {
                    if (response.getUserId() == 0) {
                        Toast.makeText(Settings.this, "Username does not exist", Toast.LENGTH_SHORT).show();
                        userToInvite.setText("");
                    } else {
                        GetUserAssociationApi().getUserAssociation(userInfo.getString("USERNAME")).enqueue(new SlimCallback<List<UserAssociation>>(response4 -> {
                            if (chooseCorrectGroup(response4, userInfo).getRole().equals("owner") || chooseCorrectGroup(response4, userInfo).getRole().equals("admin")) {
                                GetGroupApi().getGroupByName(userInfo.getString("GROUP_NAME")).enqueue(new SlimCallback<Group>(response2 -> {
                                    UserAssociation user = new UserAssociation();
                                    user.setGroupId(response2.getGroupId());
                                    user.setUserName(userToInvite.getText().toString());
                                    user.setRole("user");
                                    GetUserAssociationApi().addUserAssociation(user).enqueue(new SlimCallback<UserAssociation>(response3 -> {
                                    }));
                                }));
                            } else {
                                Toast.makeText(Settings.this, "You must be an owner or admin to do this", Toast.LENGTH_SHORT).show();
                            }
                        }));
                    }
                }));
            }
        });

        removeFromGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetUserApi().getUser(userToRemove.getText().toString()).enqueue(new SlimCallback<User>(response -> {
                    if (response.getUserId() == 0) {
                        Toast.makeText(Settings.this, "Username does not exist", Toast.LENGTH_SHORT).show();
                        userToRemove.setText("");
                    } else {
                        GetUserAssociationApi().getUserAssociation(userInfo.getString("USERNAME")).enqueue(new SlimCallback<List<UserAssociation>>(response4 -> {
                            if (chooseCorrectGroup(response4, userInfo).getRole().equals("owner")) {
                                GetUserAssociationApi().getUserAssociation(userToRemove.getText().toString()).enqueue(new SlimCallback<List<UserAssociation>>(response5 -> {
                                    GetUserAssociationApi().deleteUserAssociation(chooseCorrectGroup(response5, userInfo).getId()).enqueue(new SlimCallback<UserAssociation>(response3 -> {
                                    }));
                                }));
                            }
                            else {
                                Toast.makeText(Settings.this, "You must be an owner to do this", Toast.LENGTH_SHORT).show();
                            }
                        }));
                    }
                }));
            }
        });
    }
}