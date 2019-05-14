package com.example.acer.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Reg extends AppCompatActivity {
    EditText email,username,password;
    DBhelper mydb;
    TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        mydb= new DBhelper(this);
        email=findViewById(R.id.email);
        username=findViewById(R.id.username);
        password=findViewById(R.id.editText3);
        register=findViewById(R.id.reg);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.reg) {
                    final String Email = email.getText().toString().trim();
                    final String Username = username.getText().toString().trim();
                    final String Password = password.getText().toString().trim();
                    if(Email.isEmpty() || Username.isEmpty() || Password.isEmpty())
                    {
                        Toast.makeText(Reg.this,"Enter all details",Toast.LENGTH_SHORT).show();
                    }
                    else
                        {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Reg.this);
                            builder.setMessage("Do you want to register???")
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            boolean result = mydb.insert(Email, Username, Password);
                                            if (result == true) {
                                                Toast.makeText(Reg.this, "Data inserted", Toast.LENGTH_SHORT).show();
                                                finish();
                                                startActivity(new Intent(Reg.this,MainActivity.class));
                                            } else {
                                                Toast.makeText(Reg.this, "Data not inserted", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    })
                                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText(Reg.this,"Try again",Toast.LENGTH_SHORT).show();
                                            finish();
                                            startActivity(new Intent(new Intent(Reg.this,MainActivity.class)));
                                        }
                                    });
                            AlertDialog alert = builder.create();
                            alert.show();
                    }
                    mydb.close();
                }
            }
        });
    }

}
