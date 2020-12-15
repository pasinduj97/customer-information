package com.example.customer_information;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class EmpLogin extends AppCompatActivity {

    private EditText mEmail, mPass;
    private TextView mTextView;
    private Button signInBtn;
    private Dialog loader;
    private FirebaseFirestore db;
    private  Integer x,y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_login);

        mEmail = findViewById(R.id.loginEmail);
        mPass = findViewById(R.id.loginPassword);
        mTextView = findViewById(R.id.textView2);
        signInBtn = findViewById(R.id.loginButton);


        db = FirebaseFirestore.getInstance();

        loader = new Dialog(EmpLogin.this);
        loader.setContentView(R.layout.progress_bar);
        loader.setCancelable(false);

        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EmpLogin.this, MainActivity.class));
            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loginUser();
            }
        });
    }

    private void loginUser(){
        final String email = mEmail.getText().toString();
        final String password = mPass.getText().toString();
        loader.show();

        if(!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            if(!password.isEmpty()){

                db.collection("User").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (DocumentSnapshot documentSnapshot : task.getResult()){

                            if(email.equals(documentSnapshot.getString("Email"))){
                                x = 1;
                            }else{
                                x = 0;
                            }

                            if(password.equals( documentSnapshot.getString("Password"))){
                                y = 1;
                            }else{
                                y = 0;
                            }

                            if(x == 1 && y == 1){

                                loader.dismiss();
                                Toast.makeText(EmpLogin.this, "Login Success", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(EmpLogin.this, EmpMainMenu.class));
                                finish();

                                x = 0;
                                y = 0;

                                break;

                            }else {

                                loader.dismiss();
                                Toast.makeText(EmpLogin.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                x = 0;
                                y = 0;
                            }

                        }
                    }
                });

            }else{
                mPass.setError("Password Cannot be Null");
                loader.dismiss();
            }
        }else if (email.isEmpty()){
            mEmail.setError("Email Cannot be Null");
            loader.dismiss();
        }else{
            mEmail.setError("Enter Correct Email ID");
            loader.dismiss();
        }
    }
}