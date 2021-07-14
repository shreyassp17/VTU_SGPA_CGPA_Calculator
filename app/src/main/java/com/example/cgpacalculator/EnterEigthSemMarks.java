package com.example.cgpacalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class EnterEigthSemMarks extends AppCompatActivity {
    EditText xx81,xx82x,xxp83,xxs84,xxi85,sgpa;
    private Button submit;
    private FirebaseAuth auth;
    private String Uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_eigth_sem_marks);

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        Uid = auth.getCurrentUser().getUid();

        xx81 = findViewById(R.id.xx81);
        xx82x = findViewById(R.id.xx82x);
        xxp83 = findViewById(R.id.xxp83);
        xxs84 = findViewById(R.id.xxs84);
        xxi85 = findViewById(R.id.xxi85);
        sgpa = findViewById(R.id.SGPA);
        sgpa.setEnabled(false);
        submit = findViewById(R.id.buttonSubmit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sub1 = xx81.getText().toString();
                String sub2 = xx82x.getText().toString();
                String sub3 = xxp83.getText().toString();
                String sub4 = xxs84.getText().toString();
                String sub5 = xxi85.getText().toString();

                if(sub1.isEmpty() || sub2.isEmpty() || sub3.isEmpty() || sub4.isEmpty() || sub5.isEmpty()){
                    Toast.makeText(EnterEigthSemMarks.this, "Please enter marks of all the subjects.", Toast.LENGTH_SHORT).show();
                }
                else{
                    float res = calculateSGPA(sub1,sub2,sub3,sub4,sub5);
                    String x = Float.toString(res);
                    sgpa.setText(x);
                    HashMap<String, Object> subjects = new HashMap<>();
                    subjects.put("Sub1",sub1);
                    subjects.put("Sub2",sub2);
                    subjects.put("Sub3",sub3);
                    subjects.put("Sub4",sub4);
                    subjects.put("Sub5",sub5);
                    subjects.put("SGPA",x);

                    firestore.collection("Scheme2018").document(Uid).collection("Eighth_Sem").document("Subjects").set(subjects).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(EnterEigthSemMarks.this, "Success", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });
    }

    float calculateSGPA(String sub1,String sub2,String sub3,String sub4,String sub5)
    {
        float SGPA;
        int s1 = Integer.parseInt(sub1);
        int s2 = Integer.parseInt(sub2);
        int s3 = Integer.parseInt(sub3);
        int s4 = Integer.parseInt(sub4);
        int s5 = Integer.parseInt(sub5);

        String[] subjects = {"18XX81","18XX82X","18XXP83","18XXS84","18XXI85"};

        HashMap<String,Integer> subCr = new HashMap<>();
        subCr.put(subjects[0],3);
        subCr.put(subjects[1],3);
        subCr.put(subjects[2],3);
        subCr.put(subjects[3],3);
        subCr.put(subjects[4],3);

        int arr[] = {s1,s2,s3,s4,s5};

        HashMap<String,Integer> subGr = new HashMap<>();
        for(int i=0;i<arr.length;i++){
            if(arr[i]>=90)
                subGr.put(subjects[i],10);
            else if(arr[i]<90 && arr[i]>=80)
                subGr.put(subjects[i],9);
            else if(arr[i]<80 && arr[i]>=70)
                subGr.put(subjects[i],8);
            else if(arr[i]<70 && arr[i]>=60)
                subGr.put(subjects[i],7);
            else if(arr[i]<60 && arr[i]>=45)
                subGr.put(subjects[i],6);
            else if(arr[i]<45 && arr[i]>=40)
                subGr.put(subjects[i],4);
            else if(arr[i]<40)
                subGr.put(subjects[i],0);
        }
        int creditsTotal = 15;
        float in1 = subCr.get(subjects[0])*subGr.get(subjects[0]);
        for(int j = 1; j<arr.length; j++){
            in1 += subCr.get(subjects[j])*subGr.get(subjects[j]);
        }
        SGPA = in1/creditsTotal;
        return SGPA;

    }
}

