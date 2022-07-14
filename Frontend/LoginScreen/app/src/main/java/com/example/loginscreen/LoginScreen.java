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
import com.example.loginscreen.model.User;

public class LoginScreen extends AppCompatActivity {

    /**
     * (Blake's comment)
     * Checks if password is correct by checking if user's password is validated
     * @param u user to check
     * @return true if password is valid, false otherwise
     */
    public boolean isCorrectPassword(User u) {
        if(u.getPassword().equals("validated")) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * (Jack's comment)
     * Checks if user exists in system
     * @param u user to check
     * @return true if user exists, false otherwise
     */
    public boolean isValidUser(User u) {
        if(u == null) {
            return false;
        }
        else {
            return true;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button loginButton = findViewById(R.id.loginButton);
        Button createAccountButton = findViewById(R.id.createAccountButton);
        EditText usernameTextEdit = findViewById(R.id.usernameTextEdit);
        EditText passwordTextEdit = findViewById(R.id.passwordTextEdit);

        //Bundle Code
        Bundle extras = new Bundle();
        Intent getLoginScreenIntent = getIntent();
        Bundle extras2 = getLoginScreenIntent.getExtras();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetUserApi().getUserValidate(usernameTextEdit.getText().toString(), passwordTextEdit.getText().toString()).enqueue(new SlimCallback<User>(response -> {
                    if(!isValidUser(response)) {
                        Toast.makeText(LoginScreen.this, "Username does not exist", Toast.LENGTH_SHORT).show();
                        usernameTextEdit.setText("");
                    }
                    else if(!isCorrectPassword(response)) {
                        Toast.makeText(LoginScreen.this, "Password is incorrect", Toast.LENGTH_SHORT).show();
                        passwordTextEdit.setText("");
                    }
                    else {
                        extras.putString("USERNAME", usernameTextEdit.getText().toString());
                        Intent homepageIntent = new Intent(LoginScreen.this, Homepage.class);
                        homepageIntent.putExtras(extras);
                        startActivity(homepageIntent);
                    }
                }, "CustomTag"));
            }
        });



        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), CreateAccount.class));
            }
        });
    }
}