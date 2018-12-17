package com.example.chanaka.myapplogin;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Adduser extends Activity{

    EditText eid,enam,eaut,eloc,ecat;
    Button btnadddata;
    Button btndelete;
    Button btnv;

    DatabaseHelper dp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.books);
        dp=new DatabaseHelper(this);

        eid=(EditText)findViewById(R.id.edit_id);
        enam=(EditText)findViewById(R.id.edit_nam);
        eaut=(EditText)findViewById(R.id.edit_aut);
        eloc=(EditText)findViewById(R.id.edit_loc);
        ecat=(EditText)findViewById(R.id.edit_cat);

        btnadddata=(Button)findViewById(R.id.btnad);
        btndelete=(Button) findViewById(R.id.btn_del);
        btnv=(Button)findViewById(R.id.btn_viw);
        AddData();
        Deletedata();
        viwall();
    }
    public void Deletedata(){

        btndelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Integer deleteRows = dp.deletedb(eid.getText().toString());

                        if(deleteRows >0)
                            Toast.makeText(Adduser.this,"Data deleted",Toast.LENGTH_LONG).show();

                        else
                            Toast.makeText(Adduser.this,"Data not deleted",Toast.LENGTH_LONG).show();



                    }



                }

        );


    }

    public void AddData(){

        btnadddata.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean inserted = dp.insertdb(eid.getText().toString(),enam.getText().toString(),eaut.getText().toString(),eloc.getText().toString(),ecat.getText().toString());

                        if(inserted=true)
                            Toast.makeText(Adduser.this,"Data inserted",Toast.LENGTH_LONG).show();

                        else
                            Toast.makeText(Adduser.this,"Data inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );


    }
    public void viwall(){

        btnv.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Cursor res = dp.getall();

                        if(res.getCount()==0){
                            showmessage("nothing found","error");
                            return;
                        }
                        StringBuffer buffer =new StringBuffer();
                        while(res.moveToNext()){

                            buffer.append("id: "+res.getString(0)+"\n");
                            buffer.append("Name: "+res.getString(1)+"\n");
                            buffer.append("author: "+res.getString(2)+"\n");
                            buffer.append("location: "+res.getString(3)+"\n");
                            buffer.append("catagory: "+res.getString(4)+"\n\n");
                        }

                        showmessage("Data",buffer.toString());
                    }
                }
        );

    }

    public  void showmessage(String title, String message){

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();


    }
}




