package com.example.chanaka.myapplogin;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends Activity {

    Logindb helper=new Logindb(this);

    private EditText name,email,user,pass1,pass2;
    private String strname,stremail,struser,strpass1,strpass2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
    }

    public void OnRegisterClick(View v){

        if(v.getId()==R.id.btnreg){

            name=(EditText)findViewById(R.id.TFname);
            email=(EditText)findViewById(R.id.TFmail);
            user=(EditText)findViewById(R.id.TFuser);
            pass1=(EditText)findViewById(R.id.TFpass1);
            pass2=(EditText)findViewById(R.id.TFpass2);

            strname= name.getText().toString();
            stremail=email.getText().toString();
            struser=user.getText().toString();
            strpass1=pass1.getText().toString();
            strpass2=pass2.getText().toString();

            if(!strpass1.equals(strpass2)){

                Toast pass=Toast.makeText(SignUp.this,"password don't match or empty",Toast.LENGTH_SHORT);
                pass.show();
            }
            else {

                Contact c =new Contact();

                c.setName(strname);
                c.setEmail(stremail);
                c.setUname(struser);
                c.setPass(strpass1);

               helper.InsertContact(c);

                Toast pass=Toast.makeText(SignUp.this," user registerd",Toast.LENGTH_SHORT);
                pass.show();


            }

        }


    }


}