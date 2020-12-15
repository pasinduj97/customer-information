package com.example.customer_information;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class EmpMainMenu extends AppCompatActivity {

    private Button signoutBtn, addNewBtn, showBtn;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_main_menu);

        signoutBtn = findViewById(R.id.buttonLogout);
        addNewBtn = findViewById(R.id.buttonAdd);
        showBtn = findViewById(R.id.buttonView);
        mAuth = FirebaseAuth.getInstance();

        signoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(EmpMainMenu.this, LoginActivity.class));
                finish();
            }
        });

        addNewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EmpMainMenu.this, EmpAddCustomerActivity.class));
                finish();
            }
        });

        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EmpMainMenu.this, EmpAllCustomers.class));
            }
        });
    }
}