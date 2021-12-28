package com.goran.zadatak3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.fonts.Font;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Contact>contacts;
    MyListAdapter adapter;
    RecyclerView recyclerView;
    DBHelper dbHelper;
    SharedPreferences sharedPreferences;
    TextView name,email,city,address,phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper=new DBHelper(this);
        recyclerView=findViewById(R.id.recyclerView);
        contacts=initializeList();
        adapter=new MyListAdapter(contacts);
        recyclerView.setAdapter(adapter);
        sharedPreferences=getSharedPreferences("settings",MODE_PRIVATE);
        setColor();
    }




    @Override
    protected void onResume() {
        super.onResume();
        contacts=initializeList();
        adapter=new MyListAdapter(contacts);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        contacts=initializeList();
        adapter=new MyListAdapter(contacts);
        recyclerView.setAdapter(adapter);
    }

    public void addContact(View view) {
        Intent i=new Intent(this,AddContact.class);
        startActivity(i);
    }
    public ArrayList<Contact> initializeList(){
        ArrayList<Contact>contacts= dbHelper.getAllContacts();
        return contacts;
    }

    public  void editContact(Contact contact){
        Intent i=new Intent(this,EditDeleteContact.class);
        i.putExtra("contact",contact);
        startActivity(i);
    }

    public void deleteAllContacts(View view) {
        String lang=sharedPreferences.getString("language","");
        if(lang.equals("English")){
            new AlertDialog.Builder(this)
                    .setTitle("Delete all contacts")
                    .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dbHelper.deletaAll();
                            contacts=initializeList();
                            adapter=new MyListAdapter(contacts);
                            recyclerView.setAdapter(adapter);
                        }
                    }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).show();
        }else{
            new AlertDialog.Builder(this)
                    .setTitle("Da li ste sigurni da zelite da izbrisete sve kontakte?")
                    .setPositiveButton("DA", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dbHelper.deletaAll();
                            contacts=initializeList();
                            adapter=new MyListAdapter(contacts);
                            recyclerView.setAdapter(adapter);
                        }
                    }).setNegativeButton("NE", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).show();
        }

    }

    public void setSettings(View view) {
        Intent i=new Intent(this,Settings.class);
        startActivity(i);
    }

    public void setColor(){
        String color=sharedPreferences.getString("color","");
        switch (color){
            case "Blue":
                findViewById(R.id.background).setBackgroundColor(getResources().getColor(R.color.blue));
                break;
            case "Red":
                findViewById(R.id.background).setBackgroundColor(getResources().getColor(R.color.red));
                break;
            case "Yellow":
                findViewById(R.id.background).setBackgroundColor(getResources().getColor(R.color.yellow));
                break;
            case "Green":
                findViewById(R.id.background).setBackgroundColor(getResources().getColor(R.color.green));
                break;
            default:
                break;

        }
    }





}