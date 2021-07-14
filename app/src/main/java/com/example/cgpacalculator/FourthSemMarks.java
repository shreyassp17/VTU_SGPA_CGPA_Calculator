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

public class FourthSemMarks extends AppCompatActivity {
    EditText mat,xx42,xx43,xx44,xx45,xx46,xxl47,xxl48,xxx49,sgpa;
    private FirebaseFirestore firestore;
    private FirebaseAuth auth;
    private String Uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_sem_marks);

        firestore = FirebaseFirestore.getInstance();


        mat = findViewById(R.id.mat41);
        xx42 = findViewById(R.id.xx42);
        xx43 = findViewById(R.id.xx43);
        xx44 = findViewById(R.id.xx44);
        xx45 = findViewById(R.id.xx45);
        xx46 = findViewById(R.id.xx46);
        xxl47 = findViewById(R.id.xxl47);
        xxl48 = findViewById(R.id.xxl48);
        xxx49 = findViewById(R.id.xxx49);
        sgpa = findViewById(R.id.sgpa);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        Uid = auth.getCurrentUser().getUid();

        DocumentReference documentReference = firestore.collection("Scheme2018").document(Uid).collection("Fourth_Sem").document("Subjects");
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                mat.setText(documentSnapshot.getString("Sub1"));
                xx42.setText(documentSnapshot.getString("Sub2"));
                xx43.setText(documentSnapshot.getString("Sub3"));
                xx44.setText(documentSnapshot.getString("Sub4"));
                xx45.setText(documentSnapshot.getString("Sub5"));
                xx46.setText(documentSnapshot.getString("Sub6"));
                xxl47.setText(documentSnapshot.getString("Sub7"));
                xxl48.setText(documentSnapshot.getString("Sub8"));
                xxx49.setText(documentSnapshot.getString("Sub9"));
                sgpa.setText(documentSnapshot.getString("SGPA"));
            }
        });
    }
}