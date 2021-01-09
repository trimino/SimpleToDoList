package com.example.simpletodolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    EditText updated_text;

    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        updated_text    = findViewById(R.id.updated_text);
        btnSave         = findViewById(R.id.btnSave);

        // set title bar
        getSupportActionBar().setTitle("Edit Item");

        // Pass the item that was tap to this Activity page
        updated_text.setText(getIntent().getStringExtra(MainActivity.KEY_ITEM_TEXT));

        // Save any changes that user has made bby clicking the "Save" button and return to MainActivity
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent that will contain the results
                Intent i = new Intent();
                // pass the data
                i.putExtra(MainActivity.KEY_ITEM_TEXT, updated_text.getText().toString());
                i.putExtra(MainActivity.KEY_ITEM_POST, getIntent().getExtras().getInt(MainActivity.KEY_ITEM_POST));
                // set the result of the intent
                setResult(RESULT_OK, i);
                // finish the edit activity and return to Main
                finish();
            }
        });


    }
}