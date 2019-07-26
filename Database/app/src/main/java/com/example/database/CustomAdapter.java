package com.example.database;

import android.content.Context;
import android.view.LayoutInflater;

import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import android.view.View;
import android.view.ViewGroup;

public class CustomAdapter extends BaseAdapter {

    Context context;
    List<Contact> contactList;
    LayoutInflater inflater;

    public CustomAdapter(Context context,List<Contact> contactList){
        this.context = context;
        this.contactList = contactList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return contactList.size();
    }

    @Override
    public Object getItem(int i) {
        return contactList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.list_view, null);
        TextView name = (TextView) view.findViewById(R.id.name);
        TextView phoneno = (TextView) view.findViewById(R.id.phone_number);
        name.setText("Name : "+contactList.get(i).getName());
        phoneno.setText("Phone Number : "+contactList.get(i).getPhoneNumber());
        return view;
    }

}
