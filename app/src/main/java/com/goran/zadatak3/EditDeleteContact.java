package com.goran.zadatak3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationChannelCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


import android.app.Notification;
import android.app.NotificationChannel;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditDeleteContact extends AppCompatActivity {
    EditText nameEdit,emailEdit,cityEdit,phoneEdit,addressEdit;
    TextView nameText,emailText,phoneText,cityText,addressText,sexText;
    TextView id;
    Contact myContact;
    Button edit,delete;
    DBHelper dbHelper;
    int counter=0;
    SharedPreferences sharedPreferences;
    NotificationManagerCompat notificationManagerCompat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_delete_contact);
        sharedPreferences=getSharedPreferences("settings",MODE_PRIVATE);
        dbHelper=new DBHelper(this);
        nameEdit=findViewById(R.id.nameEdit);
        emailEdit=findViewById(R.id.emailEdit);
        phoneEdit=findViewById(R.id.phoneEdit);
        cityEdit=findViewById(R.id.cityEdit);
        addressEdit=findViewById(R.id.addressEdit);
        id=findViewById(R.id.id);
        edit=findViewById(R.id.edit);
        delete=findViewById(R.id.delete);
        nameText=findViewById(R.id.name);
        emailText=findViewById(R.id.email);
        phoneText=findViewById(R.id.phone);
        cityText=findViewById(R.id.city);
        addressText=findViewById(R.id.address);
        sexText=findViewById(R.id.sex);

        Intent i=getIntent();
        Contact contact= (Contact) i.getSerializableExtra("contact");
        myContact=contact;
        nameEdit.setText(contact.getName());
        emailEdit.setText(contact.getEmail());
        id.setText(String.valueOf(contact.getId()));
        cityEdit.setText(contact.getCity());
        addressEdit.setText(contact.getAddress());
        phoneEdit.setText(contact.getPhoneNumber());
        notificationManagerCompat=NotificationManagerCompat.from(this);
        setLanguage();
        setFontStyle();
    }

    @Override
    protected void onStart() {
        super.onStart();
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact newContact=new Contact(nameEdit.getText().toString(),emailEdit.getText().toString(),addressEdit.getText().toString(),
                        cityEdit.getText().toString(),phoneEdit.getText().toString(),myContact.getSex());
                newContact.setId(Integer.parseInt(id.getText().toString()));
                dbHelper.updateContact(newContact);
                Notification notification;
                if(sharedPreferences.getString("language","").equals("English")) {
                     notification = new NotificationCompat.Builder(getBaseContext(), ChannelConfiguration.CHANNEL_1)
                            .setContentTitle("Updated product!")
                            .setContentText("You successfully updated product")
                            .setSmallIcon(R.drawable.ic_baseline_update_24).build();
                             notificationManagerCompat.notify(counter,notification);
                             counter++;
                }else{
                    notification = new NotificationCompat.Builder(getBaseContext(), ChannelConfiguration.CHANNEL_1)
                            .setContentTitle("Azuriran kontakt!")
                            .setContentText("Uspesno ste azurirali kontakt!")
                            .setSmallIcon(R.drawable.ic_baseline_update_24).build();
                             notificationManagerCompat.notify(counter,notification);
                             counter++;
                }

                finish();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteContact(myContact);
                Notification notification;
                if(sharedPreferences.getString("language","").equals("English")) {
                    notification = new NotificationCompat.Builder(getBaseContext(), ChannelConfiguration.CHANNEL_1)
                            .setContentTitle("Deleted product!")
                            .setContentText("You successfully deleted product")
                            .setSmallIcon(R.drawable.ic_baseline_delete_24).build();
                    notificationManagerCompat.notify(counter,notification);
                    counter++;
                }else{
                    notification = new NotificationCompat.Builder(getBaseContext(), ChannelConfiguration.CHANNEL_1)
                            .setContentTitle("Izbrisan kontakt!")
                            .setContentText("Uspesno ste izbrisali kontakt!")
                            .setSmallIcon(R.drawable.ic_baseline_delete_24).build();
                    notificationManagerCompat.notify(counter,notification);
                    counter++;
                }

                finish();
            }
        });
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
                edit.setText("Izmeni konakt");
                delete.setText("Izbrisi konakt");
                break;
            case "English":
                nameText.setText("Name");
                addressText.setText("Address");
                cityText.setText("City");
                phoneText.setText("Phone number");
                sexText.setText("Sex");
                edit.setText("Save contact");
                delete.setText("Delete contact");
                break;
            default:
                break;

        }
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

}