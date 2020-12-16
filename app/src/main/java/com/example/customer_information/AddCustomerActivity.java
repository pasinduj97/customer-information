package com.example.customer_information;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.UUID;

public class AddCustomerActivity extends AppCompatActivity {

    private EditText mFirstName, mLastName, mAddressNo,mAddress1,mAddress2,initial,postal,country, mEmail, mMobileOne, mMobileTwo, mMobileThree, mDOB, mAnniversary;
    private Button addNewBtn, newRecBtn;
    private RadioGroup type;
    private DatePickerDialog picker;
    private FirebaseFirestore db;
    private Dialog loader;
    private String cust;

    private String uId, uFirstName, uLastName, uEmail, uMobileOne, uMobileTwo, uMobileThree, uDOB, uAnniversary,ucustomertype,upostalNumber,uin,ucusCountry,uaddressNo,uaddress1,uadress2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        mFirstName = findViewById(R.id.etFirstName);
        mLastName = findViewById(R.id.etLastName);
        mAddressNo = findViewById(R.id.etAddressNo);
        mAddress1 = findViewById(R.id.etAddressLine2);
        mAddress2 = findViewById(R.id.etAddressLine3);
        initial = findViewById(R.id.Initial);
        postal = findViewById(R.id.postalcode);
        country = findViewById(R.id.country);
        type = findViewById(R.id.type);
        mEmail = findViewById(R.id.etEmail);
        mMobileOne = findViewById(R.id.etMobileOne);
        mMobileTwo = findViewById(R.id.etMobileTwo);
        mMobileThree = findViewById(R.id.etMobileThree);
        mDOB = findViewById(R.id.etDOB);
        mAnniversary = findViewById(R.id.etAnniversary);

        loader = new Dialog(AddCustomerActivity.this);
        loader.setContentView(R.layout.progress_bar);
        loader.setCancelable(false);

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
                                mDOB.setText((monthOfYear + 1)  + "/" + dayOfMonth + "/" + year);
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
                                mAnniversary.setText((monthOfYear + 1)  + "/" + dayOfMonth + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.mr:
                        ucustomertype = "Mr.";
                        break;
                    case R.id.mrs:
                        ucustomertype  = "Mrs.";
                        break;
                    case R.id.ms:
                        ucustomertype  = "Ms.";
                        break;
                    case R.id.dr:
                        ucustomertype  = "Dr.";
                        break;
                    case R.id.rev:
                        ucustomertype  = "Rev.";
                        break;


                }
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
                uaddressNo = bundle.getString("uPostalAddressNo");
                uaddress1 = bundle.getString("uPostalAddress1");
                uadress2 = bundle.getString("uPostalAddress2");
                uEmail = bundle.getString("uEmail");
                uMobileOne = bundle.getString("uMobileOne");
                uMobileTwo = bundle.getString("uMobileTwo");
                uMobileThree = bundle.getString("uMobileThree");
                uDOB = bundle.getString("uDOB");
                uAnniversary = bundle.getString("uAnniversary");
                uin = bundle.getString("uInitials");
                ucusCountry = bundle.getString("uCountry");
                ucustomertype = bundle.getString("uType");
                upostalNumber = bundle.getString("uPostalCode");


                mFirstName.setText(uFirstName);
                mLastName.setText(uLastName);
                mAddressNo.setText(uaddressNo);
                mAddress1.setText(uaddress1);
                mAddress2.setText(uadress2);
                mEmail.setText(uEmail);
                mMobileOne.setText(uMobileOne);
                mMobileTwo.setText(uMobileTwo);
                mMobileThree.setText(uMobileThree);
                mDOB.setText(uDOB);
                initial.setText(uin);
                postal.setText(upostalNumber);
                country.setText(ucusCountry);
                mAnniversary.setText(uAnniversary);

                switch (ucustomertype){

                    case "Mr.":
                        type.check(R.id.mr);
                        break;
                    case "Mrs.":
                        type.check(R.id.mrs);
                        break;
                    case "Ms.":
                        type.check(R.id.ms);
                        break;
                    case "Dr.":
                        type.check(R.id.dr);
                        break;
                    case "Rev.":
                        type.check(R.id.rev);
                        break;

                }


        }else{
            addNewBtn.setText("Add Record");
        }

        addNewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName= mFirstName.getText().toString();
                String lastName = mLastName.getText().toString();
                String adrNos = mAddressNo.getText().toString();
                String adress1s = mAddress1.getText().toString();
                String adress2s = mAddress2.getText().toString();
                String initials = initial.getText().toString();
                String postals = postal.getText().toString();
                String countrys = country.getText().toString();
                String email = mEmail.getText().toString();
                String mobileOne = mMobileOne.getText().toString();
                String mobileTwo = mMobileTwo.getText().toString();
                String mobileThree = mMobileThree.getText().toString();
                String dob = mDOB.getText().toString();
                String anniversary = mAnniversary.getText().toString();

                Bundle bundle1 = getIntent().getExtras();
                if(bundle1 != null){
                            String id = uId;
                            updateToFireStore(id, firstName, lastName, adrNos,adress1s,adress2s, email, mobileOne, mobileTwo, mobileThree, dob, anniversary, initials,postals,countrys,ucustomertype);
                }else{
                    String id = UUID.randomUUID().toString();
                    saveToFireStore(id, firstName, lastName, adrNos,adress1s,adress2s, email, mobileOne, mobileTwo, mobileThree, dob, anniversary,initials,postals,countrys,ucustomertype);
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

    private void updateToFireStore(String id, String firstName, String lastName, String postalAddressNo,String postalAddress1,String postalAddress2, String email, String mobileOne,
                                   String mobileTwo, String mobileThree, String dob, String anniversary,String in, String postalCode,String cunt, String typ){

        loader.show();
        db.collection("Documents").document(id).update("firstName", firstName, "lastName", lastName,
                "doorNo", postalAddressNo, "addressLine1", postalAddress1, "addressLine2", postalAddress2,"email", email, "mobileOne", mobileOne, "mobileTwo", mobileTwo, "mobileThree", mobileThree,
                "dob", dob, "anniversary", anniversary, "initials",in, "postalCode",postalCode , "country",cunt, "type",typ  )
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(AddCustomerActivity.this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                            loader.dismiss();
                        }else{
                            Toast.makeText(AddCustomerActivity.this, "Data Not Updated" +task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            loader.dismiss();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddCustomerActivity.this, e.getMessage(),  Toast.LENGTH_SHORT).show();
                loader.dismiss();
            }
        });
    }

    private void saveToFireStore(String id, String firstName, String lastName, String postalAddressNo,String postalAddress1,String postalAddress2, String email, String mobileOne,
                                 String mobileTwo, String mobileThree, String dob, String anniversary,String in, String postalCode,String cunt, String typ){

        loader.show();

        if(!firstName.isEmpty() && !postalAddressNo.isEmpty() && !postalAddress1.isEmpty() && !postalAddress2.isEmpty() && !mobileOne.isEmpty() && !email.isEmpty() && !dob.isEmpty() && !in.isEmpty() && !postalCode.isEmpty() && !cunt.isEmpty() && !typ.isEmpty()){
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", id);
            map.put("firstName", firstName);
            map.put("lastName", lastName);
            map.put("doorNo", postalAddressNo);
            map.put("addressLine1", postalAddress1);
            map.put("addressLine2", postalAddress2);
            map.put("email", email);
            map.put("mobileOne", mobileOne);
            map.put("mobileTwo",  mobileTwo);
            map.put("mobileThree", mobileThree);
            map.put("dob", dob);
            map.put("anniversary", anniversary);
            map.put("initials",in);
            map.put("postalCode",postalCode);
            map.put("country",cunt);
            map.put("type",typ);

            db.collection("Documents").document(id).set(map)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(AddCustomerActivity.this, "Data Added Successfully", Toast.LENGTH_SHORT).show();
                                mFirstName.setText("");
                                mLastName.setText("");
                                mAddressNo.setText("");
                                mAddress1.setText("");
                                mAddress2.setText("");
                                initial.setText("");
                                country.setText("");
                                postal.setText("");
                                mEmail.setText("");
                                mMobileOne.setText("");
                                mMobileTwo.setText("");
                                mMobileThree.setText("");
                                mDOB.setText("");
                                mAnniversary.setText("");
                                loader.dismiss();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(AddCustomerActivity.this, "Data Not Added", Toast.LENGTH_SHORT).show();
                    loader.dismiss();
                }
            });

        }else {
            loader.dismiss();
            Toast.makeText(this, "The Main Fields Cannot be Null", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AddCustomerActivity.this, MainMenuActivity.class));
    }

}