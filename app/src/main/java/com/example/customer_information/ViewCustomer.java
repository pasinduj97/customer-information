package com.example.customer_information;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ViewCustomer extends AppCompatActivity {

    TextView name,address,dob,aniversry,email,land,mob1,mob2;
    String mobile1,mobile2,landline,mail;
    CardView opt1, opt2, optlan, opt4;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_customer);

        name = findViewById(R.id.name);
        address = findViewById(R.id.address);
        dob = findViewById(R.id.dob);
        aniversry = findViewById(R.id.ani);
        email = findViewById(R.id.email);
        land = findViewById(R.id.landline);
        mob1 = findViewById(R.id.mobile1);
        mob2 = findViewById(R.id.mobile2);

        opt1 = findViewById(R.id.opt1);
        optlan = findViewById(R.id.opt3);
        opt2 = findViewById(R.id.opt2);
        opt4 = findViewById(R.id.opt4);


        Intent intent = getIntent();

        landline = intent.getExtras().get("land").toString();
        mobile2  = intent.getExtras().get("mobile2").toString();
        mobile1  = intent.getExtras().get("mobile1").toString();
        mail     = intent.getExtras().get("email").toString();

        name.setText(intent.getExtras().get("first").toString() + " " + intent.getExtras().get("last").toString());
        address.setText(intent.getExtras().get("address").toString());
        dob.setText(intent.getExtras().get("dob").toString());
        aniversry.setText(intent.getExtras().get("anniversary").toString());
        email.setText(intent.getExtras().get("email").toString());
        mob1.setText(intent.getExtras().get("mobile1").toString());
        mob2.setText(intent.getExtras().get("mobile2").toString());
        land.setText(intent.getExtras().get("land").toString());


        call();
    }

    public void call(){


            opt1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:"+mobile1));
                    startActivity(intent);
                }
            });

        opt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+mobile2));
                startActivity(intent);


            }
        });

        optlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+landline));
                startActivity(intent);
            }
        });

        opt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent email= new Intent(Intent.ACTION_SENDTO);
                email.setData(Uri.parse("mailto:"+mail));
//                email.putExtra(Intent.EXTRA_SUBJECT, "Subject");
//                email.putExtra(Intent.EXTRA_TEXT, "My Email message");
                startActivity(email);
            }
        });

        opt1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                String url = "https://api.whatsapp.com/send?phone=+94"+mobile1;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

                return false;
            }
        });

        opt2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                String url = "https://api.whatsapp.com/send?phone=+94"+mobile2;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

                return false;
            }
        });




    }
}