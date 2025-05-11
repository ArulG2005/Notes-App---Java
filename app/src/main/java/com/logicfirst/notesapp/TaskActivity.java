package com.logicfirst.notesapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import io.realm.Realm;

public class TaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        EditText title=findViewById(R.id.title);
        EditText description=findViewById(R.id.description);
        Button save=findViewById(R.id.save);
        Realm.init(getApplicationContext());
        Realm realm=Realm.getDefaultInstance();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title1=title.getText().toString();
                String description1=description.getText().toString();
                realm.beginTransaction();
                Note note=realm.createObject(Note.class);
                note.setTitle(title1);
                note.setDescription(description1);
                note.setCreatedAt(System.currentTimeMillis());
               realm.commitTransaction();
                Toast.makeText(getApplicationContext(),"Note Saved",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}