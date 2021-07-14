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

public class SeventhSemMarks extends AppCompatActivity {
    EditText xx71,xx72,xx73x,xx74x,xx75x,xxl76,xxp77,sgpa;
    private FirebaseFirestore firestore;
    private FirebaseAuth auth;
    private String Uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seventh_sem_marks);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        Uid = auth.getCurrentUser().getUid();

        xx71 = findViewById(R.id.xx71);
        xx72 = findViewById(R.id.xx72);
        xx73x = findViewById(R.id.xx73x);
        xx74x = findViewById(R.id.xx74x);
        xx75x = findViewById(R.id.xx75x);
        xxl76 = findViewById(R.id.xxl76);
        xxp77 = findViewById(R.id.xxp77);
        sgpa = findViewById(R.id.sgpa);


        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        Uid = auth.getCurrentUser().getUid();

        DocumentReference documentReference = firestore.collection("Scheme2018").document(Uid).collection("Seventh_Sem").document("Subjects");
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                xx71.setText(documentSnapshot.getString("Sub1"));
                xx72.setText(documentSnapshot.getString("Sub2"));
                xx73x.setText(documentSnapshot.getString("Sub3"));
                xx74x.setText(documentSnapshot.getString("Sub4"));
                xx75x.setText(documentSnapshot.getString("Sub5"));
                xxl76.setText(documentSnapshot.getString("Sub6"));
                xxp77.setText(documentSnapshot.getString("Sub7"));
                sgpa.setText(documentSnapshot.getString("SGPA"));
            }
        });
    }
}