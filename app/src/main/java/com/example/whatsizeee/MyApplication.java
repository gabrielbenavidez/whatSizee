package com.example.whatsizeee;

import android.app.Application;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {
    public static List<SizeEntry> sizeEntries = new ArrayList<SizeEntry>();
    private static int nextId = 0;
    private static Context context = null;
    DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());

    public MyApplication() {
    }

    public static void setContext(Context context){
        MyApplication.context = context;
    }

    public static Context getContext(){
        return MyApplication.context;
    }

    public List<SizeEntry> getSizeEntries() {
        List<SizeEntry> entriesList = new ArrayList<>();

        for(String entry : dbHelper.getEntries()){
            //SizeEntry sizeEntry = new SizeEntry(nextId, nameEditText.getText().toString(), imageUri, topEditText.getText().toString(), bottomEditText.getText().toString(), shoeEditText.getText().toString(), ringEditText.getText().toString());
            //entriesList.add(sizeEntry);
        }
        return entriesList;
    }

    public void addEntry(SizeEntry entry){
        dbHelper.addEntry(entry);
    }

    public static void setSizeEntries(List<SizeEntry> sizeEntries) {
        MyApplication.sizeEntries = sizeEntries;
    }

    public static int getNextId() {
        return MyApplication.nextId;
    }

    public static void incrementId() {
        MyApplication.nextId++;
    }

    public static void decrementId() {
        MyApplication.nextId--;
    }


}
