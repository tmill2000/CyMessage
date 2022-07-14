package com.example.loginscreen;

import androidx.appcompat.app.AppCompatActivity;
import static com.example.loginscreen.api.ApiClientFactory.GetUserApi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginscreen.api.SlimCallback;
import com.example.loginscreen.model.User;


public class CreateAccount extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        Button createAccountButton = findViewById(R.id.finishCreateButton);
        Button cancelCreateButton = findViewById(R.id.cancelCreateButton);

        EditText usernameTextEdit = findViewById(R.id.createUsernameTextEdit);
        EditText passwordTextEdit = findViewById(R.id.createPasswordTextEdit);
        EditText emailTextEdit = findViewById(R.id.emailTextEdit);
        EditText passwordConfirmTextEdit = findViewById(R.id.confirmPasswordTextEdit);



        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!passwordTextEdit.getText().toString().equals(passwordConfirmTextEdit.getText().toString())) {
                    Toast.makeText(CreateAccount.this, "Passwords don't match", Toast.LENGTH_SHORT).show();
                    passwordTextEdit.setText("");
                    passwordConfirmTextEdit.setText("");
                }
                else {
                    User user = new User();
                    user.setEmail(emailTextEdit.getText().toString());
                    user.setUserName(usernameTextEdit.getText().toString());
                    user.setPassword(passwordTextEdit.getText().toString());
                    GetUserApi().addUser(user).enqueue(new SlimCallback<User>(response2 -> {
                    }));
                    startActivity(new Intent(view.getContext(), LoginScreen.class));
                }

            }

        });

        cancelCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), LoginScreen.class));
            }
        });
    }
}