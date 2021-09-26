package com.example.firebasefirestoreapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore db;
    EditText etName;
    Button btnAdd,btnRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //firestore database
        db=FirebaseFirestore.getInstance();

        //edittexts
        etName=findViewById(R.id.etName);

        //buttons
        btnAdd=findViewById(R.id.btnAdd);
        btnRead=findViewById(R.id.btnRead);

        //onclick on btnAdd
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=etName.getText().toString();

                //hashmap object
                HashMap hashMap=new HashMap();
                hashMap.put("Name",name);

                db.collection("Student").document("Data").set(hashMap)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(MainActivity.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Failed Data Insertion", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}