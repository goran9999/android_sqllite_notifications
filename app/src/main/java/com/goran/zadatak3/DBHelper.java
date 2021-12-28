package com.goran.zadatak3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "contacts", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("CREATE TABLE contact(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,email TEXT,sex TEXT," +
                    " phone_number TEXT,city TEXT,address TEXT)");
        }catch (Exception e){
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<Contact> getAllContacts(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM contact",null);
        ArrayList<Contact>contacts=new ArrayList<>();
        Contact contact;
        while(cursor.moveToNext()){
            contact=new Contact();
            contact.setId(cursor.getInt(0));
            contact.setName(cursor.getString(1));
            contact.setEmail(cursor.getString(2));
            contact.setSex(Pol.valueOf(cursor.getString(3)));
            contact.setPhoneNumber(cursor.getString(4));
            contact.setCity(cursor.getString(5));
            contact.setAddress(cursor.getString(6));
            contacts.add(contact);
        }
        db.close();
        return  contacts;
    }

    public void addContact(Contact contact){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("name",contact.getName());
        contentValues.put("email",contact.getEmail());
        contentValues.put("sex",contact.getSex().toString());
        contentValues.put("phone_number",contact.getPhoneNumber());
        contentValues.put("city",contact.getCity());
        contentValues.put("address",contact.getAddress());

        db.insert("contact",null,contentValues);
        db.close();
    }

    public void deleteContact(Contact contact){
        SQLiteDatabase db=this.getReadableDatabase();
        db.execSQL("DELETE FROM contact WHERE id=?",new String[]{String.valueOf(contact.getId())});
        db.close();
    }

    public void updateContact(Contact contact){
        SQLiteDatabase db=this.getWritableDatabase();
        String name=contact.getName();
        String email=contact.getEmail();
        String city=contact.getCity();
        String address=contact.getAddress();
        String phone=contact.getPhoneNumber();
        int id=contact.getId();
        ContentValues contentValues=new ContentValues();
        contentValues.put("name",name);
        contentValues.put("email",email);
        contentValues.put("sex",contact.getSex().toString());
        contentValues.put("phone_number",contact.getPhoneNumber());
        contentValues.put("city",city);
        contentValues.put("address",address);

        int brojRedova=db.update("contact",contentValues,"id=?",new String[]{String.valueOf(id)});
        name="agasg";
    }

    public void deletaAll(){
        SQLiteDatabase db=this.getReadableDatabase();
        db.execSQL("DELETE FROM contact");
        db.close();
    }

}
