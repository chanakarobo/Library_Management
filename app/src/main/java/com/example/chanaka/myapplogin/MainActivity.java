package com.example.chanaka.myapplogin;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {

    Logindb helper = new Logindb(this);


    private EditText a, b;
    private String uname, pass;


    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_login);

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

    }

    public void onButtonClick(View v) {

        if (v.getId() == R.id.btn_login) {
            a = (EditText) findViewById(R.id.TFusername);
            uname = a.getText().toString();

            b = (EditText) findViewById(R.id.TFpass);
            pass = b.getText().toString();

            if (helper.seachPass(uname, pass)) {

                Intent i = new Intent(MainActivity.this, Adduser.class);
                startActivity(i);
            } else {

                Toast temp = Toast.makeText(MainActivity.this, "user name and password don't match", Toast.LENGTH_SHORT);
                temp.show();

            }

        }

        if (v.getId() == R.id.btnsignup) {

            Intent i = new Intent(MainActivity.this, SignUp.class);
            startActivity(i);
        }


    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.chanaka.myapplogin/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.chanaka.myapplogin/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
