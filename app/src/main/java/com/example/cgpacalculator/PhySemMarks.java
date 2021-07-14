package com.example.cgpacalculator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;

public class PhySemMarks extends AppCompatActivity {
    private EditText matx1,phyx2,elex3,civx4,egdlx5,phylx6,elelx7,eghx8;
    private EditText sgpa;
    private FirebaseAuth auth;
    private FirebaseFirestore firestore;
    private String Uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phy_sem_marks);
        matx1 = findViewById(R.id.matx1);
        phyx2 =  findViewById(R.id.phyX2);
        elex3 = findViewById(R.id.elex3);
        civx4 = findViewById(R.id.civx4);
        egdlx5 = findViewById(R.id.egdlx5);
        phylx6 = findViewById(R.id.phylx6);
        elelx7 = findViewById(R.id.elelx7);
        eghx8 = findViewById(R.id.eghx8);
        sgpa = findViewById(R.id.sgpa);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        Uid = auth.getCurrentUser().getUid();

        DocumentReference documentReference = firestore.collection("Scheme2018").document(Uid).collection("Phy_Cycle").document("Subjects");
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                matx1.setText(documentSnapshot.getString("Sub1"));
                phyx2.setText(documentSnapshot.getString("Sub2"));
                elex3.setText(documentSnapshot.getString("Sub3"));
                civx4.setText(documentSnapshot.getString("Sub4"));
                egdlx5.setText(documentSnapshot.getString("Sub5"));
                phylx6.setText(documentSnapshot.getString("Sub6"));
                elelx7.setText(documentSnapshot.getString("Sub7"));
                eghx8.setText(documentSnapshot.getString("Sub8"));
                sgpa.setText(documentSnapshot.getString("SGPA"));
            }
        });

    }
}