package vistula.hm.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText editTextNewUserName,editTextNewPassword,editTextPasswordConfirm,editTextEmail;
    String username,password,passwordConfirmed,email;
    Button proceed;
    boolean isValid;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editTextEmail = findViewById(R.id.editTextTextEmailAddress);
        editTextNewPassword = findViewById(R.id.editTextTextPassword);
        editTextPasswordConfirm = findViewById(R.id.editTextTextPassword2);
        editTextNewUserName = findViewById(R.id.editNewUsername);
        proceed = (Button)findViewById(R.id.btnRegister);
        dbHelper =new DBHelper(this);
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isValid=validate();
                if(isValid){
                    addNewUser();
                    Toast.makeText(getApplicationContext(),"Welcome New User!", Toast.LENGTH_SHORT).show();
                    toMain(view);
                }
            }
        });
    }
    private void addNewUser(){
        username = editTextNewUserName.getText().toString();
        password = editTextNewPassword.getText().toString();
        passwordConfirmed = editTextPasswordConfirm.getText().toString();
        boolean checkUser = dbHelper.userNameExist(username);
        if (!checkUser){
            boolean insertUser = dbHelper.insertUser(username,password);
            if (insertUser){
                return;
            }
            else editTextNewUserName.setError("Failed to Register. Contact Support");
        }
        else editTextNewUserName.setError("Username already exists");
    }
    private boolean validate(){
        username = editTextNewUserName.getText().toString();
        password = editTextNewPassword.getText().toString();
        passwordConfirmed = editTextPasswordConfirm.getText().toString();
        email = editTextEmail.getText().toString();
        if (username.isEmpty()){
            editTextNewUserName.setError("Username cannot be empty!");

            return false;
        } else if (!username.isEmpty()) {
            boolean checkUser = dbHelper.userNameExist(username);
            if (checkUser){
                editTextNewUserName.setError("Username already exists!");
                return false;
            }
        }
        if(password.isEmpty()||password.length()<7){
            editTextNewPassword.setError("Password cannot be empty or less than 7 characters!");
            return false;
        }
        if(!passwordConfirmed.equals(password)){
            editTextPasswordConfirm.setError("Passwords do not match");
            return false;
        }
        if (!email.endsWith("com")) {
            editTextEmail.setError("Incorrect email");
            return false;
        }
        return true;
    }
    public void toMain(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}