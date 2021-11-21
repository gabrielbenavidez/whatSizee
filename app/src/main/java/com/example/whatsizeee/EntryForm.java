package com.example.whatsizeee;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.List;

public class EntryForm extends AppCompatActivity {
    Button cancelButton, submitEntryButton;
    List<SizeEntry> sizeEntries;
    ImageButton entryAvatar;
    EditText nameEditText, topEditText, bottomEditText, shoeEditText, ringEditText;
    SizeEntry entry = null;
    int id;
    boolean entryExists = false;
    Uri imageUri;
    private static final int PICK_IMAGE = 100;
    MyApplication myApplication = (MyApplication) this.getApplication();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_form);

        //get entries
        sizeEntries = myApplication.getSizeEntries();

        //assign button ids to variables
        cancelButton = findViewById(R.id.cancelButton);
        submitEntryButton = findViewById(R.id.submitEntryButton);
        entryAvatar = (ImageButton) findViewById(R.id.entryAvatar);
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        topEditText = (EditText) findViewById(R.id.topEditText);
        bottomEditText = (EditText) findViewById(R.id.bottomEditText);
        shoeEditText = (EditText) findViewById(R.id.shoeEditText);
        ringEditText = (EditText) findViewById(R.id.ringEditText);

        //check if entry exists (boolean)
        entryExists = hasEntries();

        if (entryExists) {
            displayEntryValues();
        }
        else{
            entryAvatar.setImageResource(R.drawable.add_photo_icon);
        }

        submitEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (entryExists) {
                    updateEntry();
                } else {
                    createEntry();
                }
                //return to main activity
                Intent startIntent = new Intent(EntryForm.this, MainActivity.class);
                startActivity(startIntent);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(EntryForm.this, MainActivity.class);
                startActivity(startIntent);
            }
        });

        entryAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

    }

    private void createEntry() {
        //create new entry
        int nextId = myApplication.getNextId();

        SizeEntry newEntry = new SizeEntry(nextId, nameEditText.getText().toString(), imageUri, topEditText.getText().toString(), bottomEditText.getText().toString(), shoeEditText.getText().toString(), ringEditText.getText().toString());
        myApplication.addEntry(newEntry);

        //increment index
        myApplication.incrementId();
    }

    private void updateEntry() {
        //edit entry
        SizeEntry updatedEntry = new SizeEntry(id, nameEditText.getText().toString(), imageUri, topEditText.getText().toString(), bottomEditText.getText().toString(), shoeEditText.getText().toString(), ringEditText.getText().toString());
        sizeEntries.set(id, updatedEntry);
    }


    private void displayEntryValues() {
        for (SizeEntry e : sizeEntries) {
            if (e.getId() == id) {
                entry = e;
            }
        }
        nameEditText.setText(entry.getName());
        topEditText.setText((entry.getTopSize()));
        bottomEditText.setText((entry.getBottomSize()));
        shoeEditText.setText((entry.getShoeSize()));
        ringEditText.setText((entry.getRingSize()));
        if(entry.getImageURI() == null){
            entryAvatar.setImageResource(R.drawable.add_photo_icon);
        }
        else{
            imageUri = entry.getImageURI();
            Glide.with(EntryForm.this).load(entry.getImageURI()).into(entryAvatar);
        }
    }

    private Boolean hasEntries() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", -1);
        return id >= 0 ? true : false;
    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try{
            if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
                imageUri = data.getData();
                Glide.with(EntryForm.this).load(imageUri).into(entryAvatar);
            }
        }
        catch(Exception e){
            System.out.println("Error loading image");
        }
    }

}