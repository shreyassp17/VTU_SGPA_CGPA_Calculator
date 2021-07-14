package com.example.cgpacalculator;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class ChemSemMarks extends AppCompatActivity {
    private EditText mat,che,cps,eln,me,chel,cpl,egh,sgpa;
    private FirebaseAuth auth;
    private FirebaseFirestore firestore;
    private String Uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chem_sem_marks);
        mat = findViewById(R.id.matx1);
        che = findViewById(R.id.cheX2);
        cps = findViewById(R.id.cpsx3);
        eln = findViewById(R.id.elnx4);
        me= findViewById(R.id.mex5);
        chel = findViewById(R.id.chelx6);
        cpl = findViewById(R.id.cpllx7);
        egh = findViewById(R.id.eghx8);
        sgpa = findViewById(R.id.sgpa);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        Uid = auth.getCurrentUser().getUid();

        DocumentReference documentReference = firestore.collection("Scheme2018").document(Uid).collection("Chem_Cycle").document("Subjects");
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                mat.setText(documentSnapshot.getString("Sub1"));
                che.setText(documentSnapshot.getString("Sub2"));
                cps.setText(documentSnapshot.getString("Sub3"));
                eln.setText(documentSnapshot.getString("Sub4"));
                me.setText(documentSnapshot.getString("Sub5"));
                chel.setText(documentSnapshot.getString("Sub6"));
                cpl.setText(documentSnapshot.getString("Sub7"));
                egh.setText(documentSnapshot.getString("Sub8"));
                sgpa.setText(documentSnapshot.getString("SGPA"));
            }
        });
    }
}