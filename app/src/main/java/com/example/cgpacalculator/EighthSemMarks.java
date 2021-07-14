package com.example.cgpacalculator;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class EighthSemMarks extends AppCompatActivity {
    EditText xx81,xx82x,xxp83,xxs84,xxi85,sgpa;
    private Button submit;
    private FirebaseFirestore firestore;
    private FirebaseAuth auth;
    private String Uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eighth_sem_marks);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        Uid = auth.getCurrentUser().getUid();

        xx81 = findViewById(R.id.xx81);
        xx82x = findViewById(R.id.xx82x);
        xxp83 = findViewById(R.id.xxp83);
        xxs84 = findViewById(R.id.xxs84);
        xxi85 = findViewById(R.id.xxi85);
        sgpa = findViewById(R.id.sgpa);


        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        Uid = auth.getCurrentUser().getUid();

        DocumentReference documentReference = firestore.collection("Scheme2018").document(Uid).collection("Eighth_Sem").document("Subjects");
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                xx81.setText(documentSnapshot.getString("Sub1"));
                xx82x.setText(documentSnapshot.getString("Sub2"));
                xxp83.setText(documentSnapshot.getString("Sub3"));
                xxs84.setText(documentSnapshot.getString("Sub4"));
                xxi85.setText(documentSnapshot.getString("Sub5"));;
                sgpa.setText(documentSnapshot.getString("SGPA"));
            }
        });
    }
}