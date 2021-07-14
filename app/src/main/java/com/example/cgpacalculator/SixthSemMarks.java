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

public class SixthSemMarks extends AppCompatActivity {
    EditText xx61,xx62,xx63,xx64x,xx65x,xxl66,xxl67,xxxx68,sgpa;
    private FirebaseFirestore firestore;
    private FirebaseAuth auth;
    private String Uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixth_sem_marks);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        Uid = auth.getCurrentUser().getUid();

        xx61 = findViewById(R.id.xx61);
        xx62 = findViewById(R.id.xx62);
        xx63 = findViewById(R.id.xx63);
        xx64x = findViewById(R.id.xx64x);
        xx65x = findViewById(R.id.xx65x);
        xxl66 = findViewById(R.id.xxl66);
        xxl67 = findViewById(R.id.xxl67);
        xxxx68 = findViewById(R.id.xxxx68);
        sgpa = findViewById(R.id.sgpa);


        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        Uid = auth.getCurrentUser().getUid();

        DocumentReference documentReference = firestore.collection("Scheme2018").document(Uid).collection("Sixth_Sem").document("Subjects");
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                xx61.setText(documentSnapshot.getString("Sub1"));
                xx62.setText(documentSnapshot.getString("Sub2"));
                xx63.setText(documentSnapshot.getString("Sub3"));
                xx64x.setText(documentSnapshot.getString("Sub4"));
                xx65x.setText(documentSnapshot.getString("Sub5"));
                xxl66.setText(documentSnapshot.getString("Sub6"));
                xxl67.setText(documentSnapshot.getString("Sub7"));
                xxxx68.setText(documentSnapshot.getString("Sub8"));
                sgpa.setText(documentSnapshot.getString("SGPA"));
            }
        });
    }
}