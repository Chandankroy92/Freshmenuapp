package com.example.chandan.freshmenuapp;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Chandan on 12-07-2016.
 */
public class UserRegistration extends Activity {
    private EditText etname;
    private EditText etemail;
    private EditText etdate;
    private EditText etpass;
    private EditText etrepass;

    DbAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_registration_layout);



        etname = (EditText) findViewById(R.id.etName);
        etemail = (EditText) findViewById(R.id.etEmail);
        etdate = (EditText) findViewById(R.id.etdate);
        etpass = (EditText) findViewById(R.id.etpass);
        etrepass = (EditText) findViewById(R.id.etrepass);

        Button submit=(Button)findViewById(R.id.btnSubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });


    }

    public void Register()  {
        //etname=(EditText) findViewById(R.id.etName);
        dbAdapter = new DbAdapter(this);
        try {
            dbAdapter.open();
        } catch (Exception e) {
            e.printStackTrace();
        }


        final String name = etname.getText().toString();
        final String email = etemail.getText().toString();
        final String dt = etdate.getText().toString();
        final String pass = etpass.getText().toString();
        final String repass = etrepass.getText().toString();

        if (name.equals("")) {
            etname.setError("Erro!!! enter the name");
        }
        if (!isValidEmail(email)) {
            etemail.setError("Invalid Email");
        }
       if (!isValidPass(pass)) {
            etpass.setError("Invalid Password");
        }
       if (repass.equals("") || !pass.equals(repass)) {
            etrepass.setError("Did't match");
        }
        if (dt.equals("") ) {
            etdate.setError("Error!! plz enter the date");
        }else if (!(validation.calculateAge(dt)> 16)){
            etdate.setError("ERRor...");
        }else {

            dbAdapter.setAllRegistration(name, email, pass, repass,dt);
            Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(UserRegistration.this,LoginActivity.class);
            startActivity(i);
            finish();
        }

    }

    private boolean isValidPass(String pass) {

        if (pass != null && pass.length() > 6) {
            return true;
        }
        return false;
    }

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    public void onBackPressed() {
        Intent login=new Intent(getBaseContext(),LoginActivity.class);

        startActivity(login);
        super.onBackPressed();
    }
}


