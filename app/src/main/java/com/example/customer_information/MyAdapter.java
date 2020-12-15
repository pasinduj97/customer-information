package com.example.customer_information;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private AllCustomersActivity activity;
    private List<Model> mList;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    public MyAdapter(AllCustomersActivity activity, List<Model> mList){
        this.activity = activity;
        this.mList = mList;
    }

    public void updateData(int position){
        Model item = mList.get(position);
        Bundle bundle = new Bundle();

        bundle.putString("uId", item.getId());
        bundle.putString("uFirstName", item.getFirstName());
        bundle.putString("uLastName", item.getLastName());
        bundle.putString("uPostalAddress", item.getPostalAddres());
        bundle.putString("uEmail", item.getEmail());
        bundle.putString("uMobileOne", item.getMobileOne());
        bundle.putString("uMobileTwo", item.getMobileTwo());
        bundle.putString("uMobileThree", item.getMobileThree());
        bundle.putString("uDOB", item.getDob());
        bundle.putString("uAnniversary", item.getAnniversary());

        Intent intent = new Intent(activity, AddCustomerActivity.class);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    public void deleteData(final int position){
        Model item = mList.get(position);

        db.collection("Documents").document(item.getId()).delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            notifyRemoved(position);
                            Toast.makeText(activity, "Record Deleted Successfully", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(activity, "Data Not Deleted" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void notifyRemoved(int position){
        mList.remove(position);
        notifyRemoved(position);
        activity.showData();
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(R.layout.item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.mfirstName.setText(mList.get(position).getFirstName());
        holder.mLastName.setText(mList.get(position).getLastName());
        holder.mAddress.setText(mList.get(position).getPostalAddres());
        holder.mEmail.setText(mList.get(position).getEmail());
        holder.mMobileOne.setText(mList.get(position).getMobileOne());
        holder.mMobileTwo.setText(mList.get(position).getMobileTwo());
        holder.mMobileThree.setText(mList.get(position).getMobileThree());
        holder.mDOB.setText(mList.get(position).getDob());
        holder.mAnniversary.setText(mList.get(position).getAnniversary());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView mfirstName, mLastName, mAddress, mEmail, mMobileOne, mMobileTwo, mMobileThree, mDOB, mAnniversary;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mfirstName = itemView.findViewById(R.id.firstName);
            mLastName = itemView.findViewById(R.id.LastName);
            mAddress = itemView.findViewById(R.id.address);
            mEmail = itemView.findViewById(R.id.email);
            mMobileOne = itemView.findViewById(R.id.mobile_one);
            mMobileTwo = itemView.findViewById(R.id.mobile_two);
            mMobileThree    = itemView.findViewById(R.id.mobile_three);
            mDOB = itemView.findViewById(R.id.dob);
            mAnniversary = itemView.findViewById(R.id.anniversary);

        }
    }

}
