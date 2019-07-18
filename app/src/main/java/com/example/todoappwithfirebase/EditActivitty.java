package com.example.todoappwithfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditActivitty extends AppCompatActivity {
    EditText title_id, desc_id;
    Button edit, delete;
    String titlesend, descsend, title, id, desc;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_activitty);
        Intent i = getIntent();
        title = i.getStringExtra("title");
        id = i.getStringExtra("id");
        desc = i.getStringExtra("description");
        edit = findViewById(R.id.btn_edit);
        delete = findViewById(R.id.btn_delete);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        title_id = findViewById(R.id.title_edit);
        desc_id = findViewById(R.id.description_edit);

        title_id.setText(title);
        desc_id.setText(desc);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editNotes();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteNotes();
            }
        });

    }

    private void editNotes() {
        titlesend = title_id.getText().toString();
        descsend = desc_id.getText().toString();

        Nodes nodes = new Nodes(id, titlesend, descsend);

        mDatabase.child("Notes").child(id).setValue(nodes).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(EditActivitty.this, "Note Updated", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            }
        });
    }

    private void deleteNotes() {
        mDatabase.child("Notes").child(id).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(EditActivitty.this, "Note Deleted", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            }
        });
    }
}
