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

public class ManageAccount extends AppCompatActivity {

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
        setContentView(R.layout.activity_manage_account);

        Button backManageAccountButton = findViewById(R.id.backManageAccountButton);
        Button changePasswordButton = findViewById(R.id.changePasswordButton);
        Button deleteAccountButton = findViewById(R.id.DeleteAccountButton);

        EditText currentpassTextEdit = findViewById(R.id.currentpassTextEdit);
        EditText newpassTextEdit = findViewById(R.id.newpassTextEdit);
        EditText confirmpassTextEdit = findViewById(R.id.confirmpassTextEdit);

        EditText passTextEdit = findViewById(R.id.passTextEdit);
        EditText confirmpass2TextEdit = findViewById(R.id.confirmpass2TextEdit);

        Bundle extras = new Bundle();

        backManageAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), Homepage.class));
            }
        });

        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                if(currentpassTextEdit != u.getpassword) {
                    Toast.makeText(ManageAccount.this, "Password is incorrect", Toast.LENGTH_SHORT).show();
                    currentpassTextEdit.setText("");
                }
                else if(newpassTextEdit != confirmpassTextEdit) {
                    Toast.makeText(ManageAccount.this, "New password incorrect or Confirm password incorrect", Toast.LENGTH_SHORT).show();
                    newpassTextEdit.setText("");
                }
                else {
                    password changes here
                }
                 */
            }
        });

        deleteAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetUserApi().getUserValidate(passTextEdit.getText().toString(), confirmpass2TextEdit.getText().toString()).enqueue(new SlimCallback<User>(response -> {
                    if(!isValidUser(response)) {
                        Toast.makeText(ManageAccount.this, "Password is incorrect", Toast.LENGTH_SHORT).show();
                        passTextEdit.setText("");
                    }
                    else if(!isCorrectPassword(response)) {
                        Toast.makeText(ManageAccount.this, "Password does not match", Toast.LENGTH_SHORT).show();
                        confirmpass2TextEdit.setText("");
                    }
                    else {
                        extras.putString("PASSWORD", passTextEdit.getText().toString());
                        Intent loginScreenIntent = new Intent(ManageAccount.this, Homepage.class);
                        loginScreenIntent.putExtras(extras);
                        startActivity(loginScreenIntent);
                    }
                }, "CustomTag"));
            }
        });

    }
}