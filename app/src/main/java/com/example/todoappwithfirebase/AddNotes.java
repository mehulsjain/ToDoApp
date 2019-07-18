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
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddNotes extends AppCompatActivity {
    Button bt1;
    ImageView iv1;
    EditText et1,et2;
    String descsend,titlesend;
    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);
        bt1 = findViewById(R.id.bt1);
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        iv1 = findViewById(R.id.iv1);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNotes();
            }
        });

    }
    private void addNotes(){
        titlesend = et1.getText().toString();
        descsend = et2.getText().toString();
        String id = mDatabase.push().getKey();
        Nodes nodes = new Nodes(id,titlesend,descsend);

        mDatabase.child("Notes").child(id).setValue(nodes).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(AddNotes.this,"Notes Added",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
            }
        });
    }
}
