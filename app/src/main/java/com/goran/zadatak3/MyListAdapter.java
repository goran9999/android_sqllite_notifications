package com.goran.zadatak3;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {

    ArrayList<Contact>contacts;
    public MyListAdapter(ArrayList<Contact>contacts){
        this.contacts=contacts;
    }
    private ViewGroup parent;
    @NonNull
    @Override
    public MyListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.parent=parent;
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View viewItem=layoutInflater.inflate(R.layout.contact_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(viewItem);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyListAdapter.ViewHolder holder, int position) {
        Contact contact=contacts.get(position);
        holder.name.setText(contact.getName());
        holder.email.setText(contact.getEmail());
        holder.phoneNumber.setText(contact.getPhoneNumber());
        if(contact.getSex().equals(Pol.MALE)){
            holder.image.setImageResource(R.drawable.male_avatar);
        }else {
            holder.image.setImageResource(R.drawable.female_avatar);
        }
        holder.city.setText(contact.getCity());
        holder.address.setText(contact.getAddress());

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent i=new Intent(v.getContext(),EditDeleteContact.class);
             i.putExtra("contact",contact);
             v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        EditText name,email,city,address,phoneNumber;
        ImageView image;
        public ViewHolder(@NonNull View view){
            super(view);
            name=view.findViewById(R.id.name);
            email=view.findViewById(R.id.email);
            city=view.findViewById(R.id.city);
            address=view.findViewById(R.id.address);
            phoneNumber=view.findViewById(R.id.phoneNumber);
            image=view.findViewById(R.id.avatar);
        }
    }

}
