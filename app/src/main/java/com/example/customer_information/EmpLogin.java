package com.example.customer_information;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EmpLogin extends AppCompatActivity {

    private EditText mEmail, mPass;
    private TextView mTextView;
    private Button signInBtn;
    private Dialog loader;
    private FirebaseFirestore db;
    private  Integer x,y;
    private SimpleDateFormat sdfo;
    Date d1,d2;
    String curDate;
    private NotificationManagerCompat notificationManager;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_login);


//        createNotificationChannel();

        mEmail = findViewById(R.id.loginEmail);
        mPass = findViewById(R.id.loginPassword);
        mTextView = findViewById(R.id.textView2);
        signInBtn = findViewById(R.id.loginButton);


        sdfo = new SimpleDateFormat("MM-dd-yyyy");
        db = FirebaseFirestore.getInstance();

        loader = new Dialog(EmpLogin.this);
        loader.setContentView(R.layout.progress_bar);
        loader.setCancelable(false);

        Intent stateIntent = new Intent(this, BroadCast.class);
        stateIntent.putExtra("id", 100);
        pendingIntent =
                PendingIntent.getBroadcast(this, 0, stateIntent, PendingIntent.FLAG_UPDATE_CURRENT);






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

//                                birthdayNotify();

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

//    public void birthdayNotify(){
//
//        curDate = DateFormat.getInstance().format( System.currentTimeMillis());
//
//
//        db.collection("Documents").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//
//                for (DocumentSnapshot documentSnapshot : task.getResult()){
//
//
//                    try {
////                        d1 = sdfo.parse(documentSnapshot.getString("dob"));
//
//                        d1 = sdfo.parse(sdfo.format(sdfo.parse(documentSnapshot.getString("dob"))));
//
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
//                    try {
//                        d2 = sdfo.parse(curDate);
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
//
//                    if(d2.compareTo(d1) == 0){
//
//                        NotificationCompat.Builder builder = new NotificationCompat.Builder(EmpLogin.this, "lemubitA")
//                                .setSmallIcon(R.drawable.ic_add_alert_black_24dp)
//                                .setContentTitle("Lemubit Academy notification")
//                                .setContentText("Hey students, this is an awesome notification...")
//                                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//                                .addAction(R.drawable.ic_add_alert_black_24dp, "Set Active", pendingIntent);
//
//
//                        notificationManager = NotificationManagerCompat.from(EmpLogin.this);
//                        notificationManager.notify(100, builder.build());
//
//                    }
//
//                }
//
//            }
//        });
//
//    }
//
//    private void createNotificationChannel() {
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            CharSequence name = "studentChannel";
//            String description = "Channel for student notifications";
//            int importance = NotificationManager.IMPORTANCE_DEFAULT;
//            NotificationChannel channel = new NotificationChannel("lemubitA", name, importance);
//            channel.setDescription(description);
//
//
//            NotificationManager notificationManager = getSystemService(NotificationManager.class);
//            notificationManager.createNotificationChannel(channel);
//        }
//    }

}