package vistula.hm.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editTextUser,editTextPassword;
    TextView textViewForgotPassword;
    Button login;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper= new DBHelper(this);
        editTextPassword = findViewById(R.id.editPassword);
        editTextUser = findViewById(R.id.editUsername);
    }
    public void toRegister(View view){
        Intent registerIntent = new Intent(this,RegisterActivity.class);
        startActivity(registerIntent);
    }

    public void toReminder(View view){
        String username = editTextUser.getText().toString();
        String password = editTextPassword.getText().toString();
        if(username.isEmpty()){
            editTextUser.setError("Username cannot be empty");
        }
        if (password.isEmpty()){
            editTextPassword.setError("Password cannot be empty");
        }
        boolean checkUser = dbHelper.userNameExist(username);
        if (checkUser){
            boolean checkPassword = dbHelper.userPasswordExist(username,password);
            if (checkPassword){
                Toast.makeText(getApplicationContext(),"Welcome "+username, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ReminderActivity.class);
                startActivity(intent);
            }
            else Toast.makeText(getApplicationContext(),"Wrong password!", Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(getApplicationContext(),"User does not exist!", Toast.LENGTH_SHORT).show();
    }
}