package com.example.customer_information;

import android.app.Dialog;
import android.content.Context;
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

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private AllCustomersActivity activity;
    private List<Model> mList;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Dialog loader;
    private Context context;


    public MyAdapter(AllCustomersActivity activity, List<Model> mList, Context context){
        this.activity = activity;
        this.mList = mList;
        this.context = context;
        loader = new Dialog(context);
        loader.setContentView(R.layout.progress_bar);
        loader.setCancelable(false);

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

        loader.show();
        db.collection("Documents").document(item.getId()).delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            notifyRemoved(position);
                            Toast.makeText(activity, "Record Deleted Successfully", Toast.LENGTH_SHORT).show();

                            loader.dismiss();
                        }else{
                            Toast.makeText(activity, "Data Not Deleted" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                            loader.dismiss();
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
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.mfirstName.setText(mList.get(position).getFirstName() + " " +mList.get(position).getLastName());
        holder.mMobileOne.setText(mList.get(position).getMobileOne());



        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),ViewCustomer.class);
                intent.putExtra("first",mList.get(position).getFirstName());
                intent.putExtra("last",mList.get(position).getLastName());
                intent.putExtra("address",mList.get(position).getPostalAddres());
                intent.putExtra("email",mList.get(position).getEmail());
                intent.putExtra("mobile1",mList.get(position).getMobileOne());
                intent.putExtra("mobile2",mList.get(position).getMobileTwo());
                intent.putExtra("land",mList.get(position).getMobileThree());
                intent.putExtra("dob",mList.get(position).getDob());
                intent.putExtra("anniversary",mList.get(position).getAnniversary());
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
