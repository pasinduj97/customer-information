package com.example.customer_information;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;



import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class EmpMyAdapter extends RecyclerView.Adapter<EmpMyAdapter.MyViewHolder> {

    private EmpAllCustomers activity;
    private List<Model> mList;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    public EmpMyAdapter(EmpAllCustomers activity, List<Model> mList){
        this.activity = activity;
        this.mList = mList;
    }

    public void updateData(int position){
        Model item = mList.get(position);
        Bundle bundle = new Bundle();

        bundle.putString("uId", item.getId());
        bundle.putString("uFirstName", item.getFirstName());
        bundle.putString("uLastName", item.getLastName());
        bundle.putString("uPostalAddressNo", item.getPostalAddresNo());
        bundle.putString("uPostalAddress1", item.getPostalAddressl1());
        bundle.putString("uPostalAddress2", item.getPostalAddresl2());
        bundle.putString("uEmail", item.getEmail());
        bundle.putString("uMobileOne", item.getMobileOne());
        bundle.putString("uMobileTwo", item.getMobileTwo());
        bundle.putString("uMobileThree", item.getMobileThree());
        bundle.putString("uDOB", item.getDob());
        bundle.putString("uAnniversary", item.getAnniversary());
        bundle.putString("uInitials", item.getInitials());
        bundle.putString("uCountry", item.getCountry());
        bundle.putString("uPostalCode", item.getPostalC());
        bundle.putString("uType", item.getType());

        Intent intent = new Intent(activity, EmpAddCustomerActivity.class);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }






    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(R.layout.item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.mfirstName.setText(mList.get(position).getFirstName() + " " +mList.get(position).getLastName());
        holder.mMobileOne.setText(mList.get(position).getMobileOne());



        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),ViewCustomer.class);
                intent.putExtra("first",mList.get(position).getFirstName());
                intent.putExtra("last",mList.get(position).getLastName());
                intent.putExtra("addressNo",mList.get(position).getPostalAddresNo());
                intent.putExtra("address1",mList.get(position).getPostalAddressl1());
                intent.putExtra("address2",mList.get(position).getPostalAddresl2());
                intent.putExtra("email",mList.get(position).getEmail());
                intent.putExtra("mobile1",mList.get(position).getMobileOne());
                intent.putExtra("mobile2",mList.get(position).getMobileTwo());
                intent.putExtra("land",mList.get(position).getMobileThree());
                intent.putExtra("dob",mList.get(position).getDob());
                intent.putExtra("anniversary",mList.get(position).getAnniversary());
                intent.putExtra("initials", mList.get(position).getInitials());
                intent.putExtra("country", mList.get(position).getCountry());
                intent.putExtra("postalCode", mList.get(position).getPostalC());
                intent.putExtra("type", mList.get(position).getType());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView mfirstName, mLastName, mAddress, mEmail, mMobileOne, mMobileTwo, mMobileThree, mDOB, mAnniversary;
        CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mfirstName = itemView.findViewById(R.id.firstName);
            mMobileOne = itemView.findViewById(R.id.textView);


            cardView = itemView.findViewById(R.id.cardview);

        }
    }

}
