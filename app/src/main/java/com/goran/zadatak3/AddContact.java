package com.goran.zadatak3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class AddContact extends AppCompatActivity {

    DBHelper dbHelper;
    EditText name,email,phoneNumber,city,address;
    TextView nameText,emailText,phoneText,cityText,addressText,sexText;
    Spinner sex;
    SharedPreferences sharedPreferences;
    Button save;
    int counter=0;
    NotificationManagerCompat notificationManagerCompat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        dbHelper=new DBHelper(this);
        name=findViewById(R.id.nameAdd);
        sex=findViewById(R.id.sexSpinner);
        email=findViewById(R.id.emailAdd);
        city=findViewById(R.id.cityAdd);
        address=findViewById(R.id.addressAdd);
        phoneNumber=findViewById(R.id.phoneAdd);
        nameText=findViewById(R.id.name);
        emailText=findViewById(R.id.email);
        phoneText=findViewById(R.id.phone);
        cityText=findViewById(R.id.city);
        addressText=findViewById(R.id.address);
        sexText=findViewById(R.id.sex);
        save=findViewById(R.id.save);
        sharedPreferences=getSharedPreferences("settings",MODE_PRIVATE);
        notificationManagerCompat=NotificationManagerCompat.from(this);
        setLanguage();
        setFontStyle();
    }

    @Override
    protected void onStart() {
        super.onStart();
        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this,R.array.pol, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sex.setAdapter(adapter);
    }

    private void setFontStyle() {
        String fontStyle=sharedPreferences.getString("style","");
        switch (fontStyle){
            case "bold":
                nameText.setTypeface(null, Typeface.BOLD);
                emailText.setTypeface(null, Typeface.BOLD);
                cityText.setTypeface(null, Typeface.BOLD);
                addressText.setTypeface(null, Typeface.BOLD);
                phoneText.setTypeface(null, Typeface.BOLD);
                sexText.setTypeface(null, Typeface.BOLD);
                break;
            case "italic":
                nameText.setTypeface(null, Typeface.ITALIC);
                emailText.setTypeface(null, Typeface.ITALIC);
                cityText.setTypeface(null, Typeface.ITALIC);
                addressText.setTypeface(null, Typeface.ITALIC);
                phoneText.setTypeface(null, Typeface.ITALIC);
                sexText.setTypeface(null, Typeface.ITALIC);
                break;
            case "normal":
                nameText.setTypeface(null, Typeface.NORMAL);
                emailText.setTypeface(null, Typeface.NORMAL);
                cityText.setTypeface(null, Typeface.NORMAL);
                addressText.setTypeface(null, Typeface.NORMAL);
                phoneText.setTypeface(null, Typeface.NORMAL);
                sexText.setTypeface(null, Typeface.NORMAL);
                break;

            default:
                break;

        }
    }

    private void setLanguage() {
        String lang=sharedPreferences.getString("language","");
        switch (lang){
            case "Srpski":
                nameText.setText("Ime");
                addressText.setText("Adresa");
                cityText.setText("Grad");
                phoneText.setText("Broj telefona");
                sexText.setText("Pol");
                save.setText("Sacvuaj konakt");
                break;
            case "English":
                nameText.setText("Name");
                addressText.setText("Address");
                cityText.setText("City");
                phoneText.setText("Phone number");
                sexText.setText("Sex");
                save.setText("Save contact");
                break;
            default:
                break;

        }
    }

    public void addNewContact(View view) {
        Notification notification;
        if(sharedPreferences.getString("language","").equals("English")){
            notification=new NotificationCompat.Builder(this,ChannelConfiguration.CHANNEL_2)
                    .setContentTitle("Added new contact!")
                    .setContentText("You successfully added new contact!")
                    .setSmallIcon(R.drawable.ic_baseline_add_24).build();
            notificationManagerCompat.notify(counter,notification);
            counter++;
        }else{
            notification=new NotificationCompat.Builder(this,ChannelConfiguration.CHANNEL_2)
                    .setContentTitle("Novi kontakt dodat!")
                    .setContentText("Uspesno ste dodali novi kontakt!")
                    .setSmallIcon(R.drawable.ic_baseline_add_24).build();
            notificationManagerCompat.notify(counter,notification);
            counter++;
        }
        Contact contact=new Contact(name.getText().toString(),email.getText().toString(), address.getText().toString(),city.getText().toString(),phoneNumber.getText().toString(), Pol.valueOf(sex.getSelectedItem().toString()));
        dbHelper.addContact(contact);
        finish();
    }
}