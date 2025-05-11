package com.logicfirst.notesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        AppCompatButton btn=findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,TaskActivity.class ));
            }
        });
        Realm.init(getApplicationContext());
        Realm realm=Realm.getDefaultInstance();
        RealmResults<Note> notelist=realm.where(Note.class).findAll();
        RecyclerView recycle=findViewById(R.id.recycle);
        recycle.setLayoutManager(new LinearLayoutManager(this));
        MyAdaptor myAdaptor=new MyAdaptor(this,notelist);
        recycle.setAdapter(myAdaptor);
notelist.addChangeListener(new RealmChangeListener<RealmResults<Note>>() {
    @Override
    public void onChange(RealmResults<Note> notes) {
        myAdaptor.notifyDataSetChanged();
    }
});
    }
}