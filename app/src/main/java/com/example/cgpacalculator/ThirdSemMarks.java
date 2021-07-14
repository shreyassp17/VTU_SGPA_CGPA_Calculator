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

public class ThirdSemMarks extends AppCompatActivity {
    EditText mat,xx32,xx33,xx34,xx35,xx36,xxl37,xxl38,xxx39,sgpa;
    private FirebaseFirestore firestore;
    private FirebaseAuth auth;
    private String Uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_sem_marks);

        firestore = FirebaseFirestore.getInstance();



        mat = findViewById(R.id.mat31);
        xx32 = findViewById(R.id.xx32);
        xx33 = findViewById(R.id.xx33);
        xx34 = findViewById(R.id.xx34);
        xx35 = findViewById(R.id.xx35);
        xx36 = findViewById(R.id.xx36);
        xxl37 = findViewById(R.id.xxl37);
        xxl38 = findViewById(R.id.xxl38);
        xxx39 = findViewById(R.id.xxx39);
        sgpa = findViewById(R.id.sgpa);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        Uid = auth.getCurrentUser().getUid();

        DocumentReference documentReference = firestore.collection("Scheme2018").document(Uid).collection("Third_Sem").document("Subjects");
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                mat.setText(documentSnapshot.getString("Sub1"));
                xx32.setText(documentSnapshot.getString("Sub2"));
                xx33.setText(documentSnapshot.getString("Sub3"));
                xx34.setText(documentSnapshot.getString("Sub4"));
                xx35.setText(documentSnapshot.getString("Sub5"));
                xx36.setText(documentSnapshot.getString("Sub6"));
                xxl37.setText(documentSnapshot.getString("Sub7"));
                xxl38.setText(documentSnapshot.getString("Sub8"));
                xxx39.setText(documentSnapshot.getString("Sub9"));
                sgpa.setText(documentSnapshot.getString("SGPA"));
            }
        });
    }
}