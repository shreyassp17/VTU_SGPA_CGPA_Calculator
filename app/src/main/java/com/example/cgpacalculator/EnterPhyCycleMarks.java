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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;

public class EnterPhyCycleMarks extends AppCompatActivity {
    private EditText mat,egdl,phy,phyl,ele,elel,civ,egh,sgpa;
    private Button submit;
    private FirebaseAuth auth;
    private String Uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_phy_cycle_marks);
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        auth = FirebaseAuth.getInstance();
        Uid = auth.getCurrentUser().getUid();

        mat = findViewById(R.id.mat);
        egdl = findViewById(R.id.egdl);
        phy = findViewById(R.id.phy);
        phyl = findViewById(R.id.phyl);
        ele = findViewById(R.id.ele);
        elel = findViewById(R.id.elel);
        civ = findViewById(R.id.civ);
        egh = findViewById(R.id.egh);
        sgpa = findViewById(R.id.SGPA);
        sgpa.setEnabled(false);
        submit = findViewById(R.id.buttonSubmit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sub1 = mat.getText().toString();
                String sub2 = phy.getText().toString();
                String sub3 = ele.getText().toString();
                String sub4 = civ.getText().toString();
                String sub5 = egdl.getText().toString();
                String sub6 = phyl.getText().toString();
                String sub7 = elel.getText().toString();
                String sub8 = egh.getText().toString();

                if(sub1.isEmpty() || sub2.isEmpty() || sub3.isEmpty() || sub4.isEmpty() || sub5.isEmpty() || sub6.isEmpty() || sub7.isEmpty() || sub8.isEmpty()){
                    Toast.makeText(EnterPhyCycleMarks.this, "Please enter marks of all the subjects.", Toast.LENGTH_SHORT).show();
                }
                else{
                    float res = calculateSGPA(sub1,sub2,sub3,sub4,sub5,sub6,sub7,sub8);
                    String x = Float.toString(res);
                    sgpa.setText(x);
                    HashMap<String, Object> subjects = new HashMap<>();
                    subjects.put("Sub1",sub1);
                    subjects.put("Sub2",sub2);
                    subjects.put("Sub3",sub3);
                    subjects.put("Sub4",sub4);
                    subjects.put("Sub5",sub5);
                    subjects.put("Sub6",sub6);
                    subjects.put("Sub7",sub7);
                    subjects.put("Sub8",sub8);
                    subjects.put("SGPA",x);

                    firestore.collection("Scheme2018").document(Uid).collection("Phy_Cycle").document("Subjects").set(subjects).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(EnterPhyCycleMarks.this, "Success", Toast.LENGTH_SHORT).show();
                    }
                });

            }
            }
        });

    }
    //function to calculate SGPA.
    float calculateSGPA(String sub1,String sub2,String sub3,String sub4,String sub5,String sub6,String sub7,String sub8)
    {
        float SGPA;
        int s1 = Integer.parseInt(sub1);
        int s2 = Integer.parseInt(sub2);
        int s3 = Integer.parseInt(sub3);
        int s4 = Integer.parseInt(sub4);
        int s5 = Integer.parseInt(sub5);
        int s6 = Integer.parseInt(sub6);
        int s7 = Integer.parseInt(sub7);
        int s8 = Integer.parseInt(sub8);

        String[] subjects = {"18MATX1","18PHYX2","18ELEX3","19CIVX4","18EGDLX5","18PHYLX6","18ELELX7","18EGHX8"};

        HashMap<String,Integer> subCr = new HashMap<>();
        subCr.put(subjects[0],4);
        subCr.put(subjects[1],4);
        subCr.put(subjects[2],3);
        subCr.put(subjects[3],3);
        subCr.put(subjects[4],3);
        subCr.put(subjects[5],1);
        subCr.put(subjects[6],1);
        subCr.put(subjects[7],1);

        int arr[] = {s1,s2,s3,s4,s5,s6,s7,s8};

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
        int creditsTotal = 20;
        float in1 = subCr.get(subjects[0])*subGr.get(subjects[0]);
        for(int j = 1; j<arr.length; j++){
            in1 += subCr.get(subjects[j])*subGr.get(subjects[j]);
        }
        SGPA = in1/creditsTotal;
      return SGPA;
    }

}