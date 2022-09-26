package com.kaywalker.new_starclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private EditText et_email,et_pw;
    private Button btn_mvRegi,btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("StarBucksUsers");

        et_email = findViewById(R.id.et_email);
        et_pw = findViewById(R.id.et_pw);

        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String strEmail = et_email.getText().toString();
                String strPw = et_pw.getText().toString();

                firebaseAuth.signInWithEmailAndPassword(strEmail, strPw).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Intent intent = new Intent(MainActivity.this, CenterActivity.class);
                            startActivity(intent);
                            finish();
                        } else{
                            Toast.makeText(MainActivity.this, "로그인에 실패하였습니다!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        btn_mvRegi = findViewById(R.id.btn_mvRegi);
        btn_mvRegi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}