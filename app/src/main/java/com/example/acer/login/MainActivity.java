package com.example.acer.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DBhelper d =new DBhelper(this);
    private EditText username;
    private EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.Username);
        password = findViewById(R.id.Password);
    }
    public void onClick(View v) {
        if(v.getId()==R.id.reg)
        {
            Intent i = new Intent(MainActivity.this,Reg.class);
            startActivity(i);
        }
       if(v.getId()==R.id.button)
        {
            String Username= username.getText().toString();
            String Password= password.getText().toString();
            if(Username.isEmpty() || Password.isEmpty())
            {
                Toast.makeText(this,"Error:Enter all details",Toast.LENGTH_SHORT).show();
            }
            else
            {
                String p= d.searchpassword(Username);
                if(p.equals(Password))
                {
                    Intent i = new Intent(MainActivity.this,detail.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(this,"Error:Username and password don't match",Toast.LENGTH_SHORT).show();
                }
            }

        }
    }
}
