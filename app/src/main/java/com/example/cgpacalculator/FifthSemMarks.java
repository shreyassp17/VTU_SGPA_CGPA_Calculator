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

public class FifthSemMarks extends AppCompatActivity {
    EditText xx51,xx52,xx53,xx54,xx55,xx56,xxl57,xxl58,civ59,sgpa;
    private FirebaseFirestore firestore;
    private FirebaseAuth auth;
    private String Uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth_sem_marks);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        Uid = auth.getCurrentUser().getUid();

        xx51 = findViewById(R.id.xx51);
        xx52 = findViewById(R.id.xx52);
        xx53 = findViewById(R.id.xx53);
        xx54 = findViewById(R.id.xx54);
        xx55 = findViewById(R.id.xx55);
        xx56 = findViewById(R.id.xx56);
        xxl57 = findViewById(R.id.xxl57);
        xxl58 = findViewById(R.id.xxl58);
        civ59 = findViewById(R.id.civ59);
        sgpa = findViewById(R.id.sgpa);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        Uid = auth.getCurrentUser().getUid();

        DocumentReference documentReference = firestore.collection("Scheme2018").document(Uid).collection("Fifth_Sem").document("Subjects");
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                xx51.setText(documentSnapshot.getString("Sub1"));
                xx52.setText(documentSnapshot.getString("Sub2"));
                xx53.setText(documentSnapshot.getString("Sub3"));
                xx54.setText(documentSnapshot.getString("Sub4"));
                xx55.setText(documentSnapshot.getString("Sub5"));
                xx56.setText(documentSnapshot.getString("Sub6"));
                xxl57.setText(documentSnapshot.getString("Sub7"));
                xxl58.setText(documentSnapshot.getString("Sub8"));
                civ59.setText(documentSnapshot.getString("Sub9"));
                sgpa.setText(documentSnapshot.getString("SGPA"));
            }
        });
    }
}