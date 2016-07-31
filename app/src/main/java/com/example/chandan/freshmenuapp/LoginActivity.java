package com.example.chandan.freshmenuapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

     EditText emailText;
     EditText passwordText;
     Button loginButton;
    DbAdapter dbAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbAdapter=new DbAdapter(this);
        try {
            dbAdapter.open();
        } catch (Exception e) {
            e.printStackTrace();
        }

        emailText = (EditText) findViewById(R.id.etemail);
        passwordText = (EditText) findViewById(R.id.etpassword);

        loginButton = (Button) findViewById(R.id.btn_login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String userEmail=emailText.getText().toString();
                String userPass=passwordText.getText().toString();

                try {
                    Cursor cursor = dbAdapter.getSinlgeEntry(userEmail);
                    cursor.moveToFirst();
                    if (userEmail.equals(cursor.getString(2)) && userPass.equals(cursor.getString(3))) {
                        Toast.makeText(LoginActivity.this, "Congrats: Login Successfull", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(LoginActivity.this, NavigationDrawer.class);
                        intent.putExtra("name",cursor.getString(1));
                        intent.putExtra("emial",cursor.getString(2));
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "User Name and password does not match", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){

                }

            }
        });
    }

}
