package com.example.customer_information;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.UUID;

public class AddCustomerActivity extends AppCompatActivity {

    private EditText mFirstName, mLastName, mAddress, mEmail, mMobileOne, mMobileTwo, mMobileThree, mDOB, mAnniversary;
    private Button addNewBtn, newRecBtn;
    private DatePickerDialog picker;
    private FirebaseFirestore db;

    private String uId, uFirstName, uLastName, uPostalAddress, uEmail, uMobileOne, uMobileTwo, uMobileThree, uDOB, uAnniversary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        mFirstName = findViewById(R.id.etFirstName);
        mLastName = findViewById(R.id.etLastName);
        mAddress = findViewById(R.id.etAddress);
        mEmail = findViewById(R.id.etEmail);
        mMobileOne = findViewById(R.id.etMobileOne);
        mMobileTwo = findViewById(R.id.etMobileTwo);
        mMobileThree = findViewById(R.id.etMobileThree);
        mDOB = findViewById(R.id.etDOB);
        mAnniversary = findViewById(R.id.etAnniversary);

        mDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(AddCustomerActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                mDOB.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        mAnniversary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(AddCustomerActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                mAnniversary.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        addNewBtn = findViewById(R.id.buttonAddRecord2);
        newRecBtn = findViewById(R.id.buttonNewRecord);

        db = FirebaseFirestore.getInstance();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            addNewBtn.setText("Update Record");

            uId = bundle.getString("uId");
            uFirstName = bundle.getString("uFirstName");
            uLastName = bundle.getString("uLastName");
            uPostalAddress = bundle.getString("uPostalAddress");
            uEmail = bundle.getString("uEmail");
            uMobileOne = bundle.getString("uMobileOne");
            uMobileTwo = bundle.getString("uMobileTwo");
            uMobileThree = bundle.getString("uMobileThree");
            uDOB = bundle.getString("uDOB");
            uAnniversary = bundle.getString("uAnniversary");


            mFirstName.setText(uFirstName);
            mLastName.setText(uLastName);
            mAddress.setText(uPostalAddress);
            mEmail.setText(uEmail);
            mMobileOne.setText(uMobileOne);
            mMobileTwo.setText(uMobileTwo);
            mMobileThree.setText(uMobileThree);
            mDOB.setText(uDOB);
            mAnniversary.setText(uAnniversary);

        }else{
            addNewBtn.setText("Add Record");
        }

        addNewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName= mFirstName.getText().toString();
                String lastName = mLastName.getText().toString();
                String postalAddress = mAddress.getText().toString();
                String email = mEmail.getText().toString();
                String mobileOne = mMobileOne.getText().toString();
                String mobileTwo = mMobileTwo.getText().toString();
                String mobileThree = mMobileThree.getText().toString();
                String dob = mDOB.getText().toString();
                String anniversary = mAnniversary.getText().toString();

                Bundle bundle1 = getIntent().getExtras();
                if(bundle1 != null){
                            String id = uId;
                            updateToFireStore(id, firstName, lastName, postalAddress, email, mobileOne, mobileTwo, mobileThree, dob, anniversary);
                }else{
                    String id = UUID.randomUUID().toString();
                    saveToFireStore(id, firstName, lastName, postalAddress, email, mobileOne, mobileTwo, mobileThree, dob, anniversary);
                }

            }
        });

        newRecBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddCustomerActivity.this, AddCustomerActivity.class));
                finish();
            }
        });
    }

    private void updateToFireStore(String id, String firstName, String lastName, String postalAddress, String email, String mobileOne,
                                    String mobileTwo, String mobileThree, String dob, String anniversary){
        db.collection("Documents").document(id).update("firstName", firstName, "lastName", lastName,
                "postalAddress", postalAddress, "email", email, "mobileOne", mobileOne, "mobileTwo", mobileTwo, "mobileThree", mobileThree,
                "dob", dob, "anniversary", anniversary)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(AddCustomerActivity.this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(AddCustomerActivity.this, "Data Not Updated" +task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddCustomerActivity.this, e.getMessage(),  Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveToFireStore(String id, String firstName, String lastName, String postalAddress, String email, String mobileOne,
                                 String mobileTwo, String mobileThree, String dob, String anniversary){
        if(!firstName.isEmpty() && !postalAddress.isEmpty() && !mobileOne.isEmpty() && !email.isEmpty() && !dob.isEmpty()){
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", id);
            map.put("firstName", firstName);
            map.put("lastName", lastName);
            map.put("postalAddress", postalAddress);
            map.put("email", email);
            map.put("mobileOne", mobileOne);
            map.put("mobileTwo",  mobileTwo);
            map.put("mobileThree", mobileThree);
            map.put("dob", dob);
            map.put("anniversary", anniversary);

            db.collection("Documents").document(id).set(map)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(AddCustomerActivity.this, "Data Added Successfully", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(AddCustomerActivity.this, "Data Not Added", Toast.LENGTH_SHORT).show();
                }
            });

        }else {
            Toast.makeText(this, "The Main Fields Cannot be Null", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AddCustomerActivity.this, MainMenuActivity.class));
    }

}