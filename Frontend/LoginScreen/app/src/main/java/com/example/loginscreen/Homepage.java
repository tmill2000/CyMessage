package com.example.loginscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Homepage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        Button manageAccountButton = findViewById(R.id.manageAccountButton);
        Button logoutButton = findViewById(R.id.logoutButton);
        Button groupEventsButton = findViewById(R.id.groupEventsButton);
        Button groupSelectButton = findViewById(R.id.groupSelectButton);
        Button changelogButton = findViewById(R.id.changelogButton);
        Button supportPageButton = findViewById(R.id.supportPageButton);
        Button createGroupButton = findViewById(R.id.createGroupButton);

        //Original Intent Code for retrieving the intent
        //Intent intent = getIntent();
        //username = intent.getStringExtra(MainActivity.EXTRA_USERNAME);
        //testTextView.setText(username);

        //Bundle Code for retrieving the bundle
        Intent getHomepageIntent = getIntent();
        Bundle extras = getHomepageIntent.getExtras();

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), LoginScreen.class));
            }

        });

        groupEventsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent groupEventsIntent = new Intent(Homepage.this, GroupEvents.class);
                groupEventsIntent.putExtras(extras);
                startActivity(groupEventsIntent);
            }
        });

        groupSelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Original Intent Code to messages.class
                //Intent messagesIntent = new Intent(Homepage.this, Messages.class);
                //messagesIntent.putExtra(EXTRA_USERNAME, username);
                //startActivity(messagesIntent);

                //Bundle code to messages.class
                Intent groupSelectIntent = new Intent(Homepage.this, GroupSelect.class);
                groupSelectIntent.putExtras(extras);
                startActivity(groupSelectIntent);
            }
        });

        changelogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), Changelog.class));
            }
        });

        supportPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), Support.class));
            }
        });

        manageAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), ManageAccount.class));
            }
        });

        createGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent groupCreateIntent = new Intent(Homepage.this, CreateGroup.class);
                groupCreateIntent.putExtras(extras);
                startActivity(groupCreateIntent);
            }
        });
    }
}