package com.example.customer_information;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class EmpAllCustomers extends AppCompatActivity {

    private RecyclerView mrecycleView;

    private FirebaseFirestore db;

    private EmpMyAdapter adapter;
    private List<Model> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_all_customers);

        mrecycleView = findViewById(R.id.EmprecyclerView);
        mrecycleView.setHasFixedSize(true);
        mrecycleView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();

        list = new ArrayList<>();
        adapter = new EmpMyAdapter(EmpAllCustomers.this, list);

        mrecycleView.setAdapter(adapter);

        ItemTouchHelper touchHelper = new ItemTouchHelper(new EmpTouchHelper(adapter));
        touchHelper.attachToRecyclerView(mrecycleView);

        showData();
    }

    public void showData(){
        db.collection("Documents").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        list.clear();
                        for(DocumentSnapshot snapshot : task.getResult()){
                            Model model = new Model(snapshot.getString("id"), snapshot.getString("firstName"),
                                    snapshot.getString("lastName"), snapshot.getString("postalAddress"), snapshot.getString("email"),
                                    snapshot.getString("mobileOne"), snapshot.getString("mobileTwo"), snapshot.getString("mobileThree"),
                                    snapshot.getString("dob"), snapshot.getString("anniversary"));

                            list.add(model);

                        }

                        adapter.notifyDataSetChanged();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(EmpAllCustomers.this, "Data Not Loaded to the Application", Toast.LENGTH_SHORT).show();
            }
        });
    }
}