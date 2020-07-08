package com.example.myapps.Adapter;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapps.DatabaseClass.AddListDatabaseClass;
import com.example.myapps.DatabaseClass.AddListDatabaseClassR;
import com.example.myapps.OrderList.ItemClickListener;
import com.example.myapps.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class OrderlistAdapter extends RecyclerView.Adapter<OrderlistAdapter.MyViewHolder> {

    List<AddListDatabaseClassR> customerorderlist;
    FragmentActivity activity;
    Boolean isSelectedAll = false;
    public List<AddListDatabaseClassR> checkcustomerlist = new ArrayList<>();
    boolean Uncheckall;

    public OrderlistAdapter(FragmentActivity activity, List<AddListDatabaseClassR> customerorderlist){

        this.activity=activity;
        this.customerorderlist=customerorderlist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.orderlist_cardview,parent,false));
        //View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.orderlist_cardview,parent,false);
        // MyViewHolder holder = newe MyViewHolder(v);
        //return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.orderlistname.setText(customerorderlist.get(position).get_Name());
        holder.orderlistordername.setText(customerorderlist.get(position).get_Order());
        holder.orderlistadditional.setText(customerorderlist.get(position).get_Additional());
        holder.orderlistdatebook.setText(customerorderlist.get(position).get_DateRegister());
        holder.orderlistorderprice.setText(customerorderlist.get(position).get_Orderprice());
        holder.orderlistadditionalprice.setText(customerorderlist.get(position).get_AdditionalPrice());

        holder.btncancelorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogBuilder  =new AlertDialog.Builder(v.getContext())
                        .setMessage("Are you sure you want to cancel this order?")
                       .setCancelable(false)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DatabaseReference deletepending= FirebaseDatabase.getInstance().getReference("Customer Pending").child(customerorderlist.get(position).get_Id());
                                DatabaseReference deletepayment= FirebaseDatabase.getInstance().getReference("Customer Payment").child(customerorderlist.get(position).get_Id());
                                DatabaseReference deletestatus= FirebaseDatabase.getInstance().getReference("Customer status").child(customerorderlist.get(position).get_Id());
                               // DatabaseReference deletepa
                                deletepending.removeValue();
                                deletepayment.removeValue();
                                deletestatus.removeValue();
                            }
                        })
                        .setNegativeButton(android.R.string.cancel,null);
                AlertDialog alertDialog=dialogBuilder.create();
                alertDialog.show();
            }
        });

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                CheckBox chk=(CheckBox) v;
                if(chk.isChecked()){
                    checkcustomerlist.add(customerorderlist.get(pos));
                 //   checklistonly(pos);
                }else if(!chk.isChecked()){
                    checkcustomerlist.remove(customerorderlist.get(pos));
                 //   setUncheckall();

                }
            }
        });


        if (!isSelectedAll){
            holder.chkadd.setChecked(false);
            checkcustomerlist.remove(customerorderlist.get(position));
        }
        else{
            holder.chkadd.setChecked(true);
            checkcustomerlist.remove(customerorderlist.get(position));
            checkcustomerlist.add(customerorderlist.get(position));
        }

    }


    public void checklistonly(int position){
        checkcustomerlist.add(customerorderlist.get(position));
    }
    public void notcheclistonly(int position){
        checkcustomerlist.remove(customerorderlist.get(position));
    }
    public void setUncheckall(){
        Uncheckall=false;
    }




       @Override
     public int getItemCount() {
          return customerorderlist.size();
      }



    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView orderlistname,orderlistordername,orderlistadditional,orderlistdatebook,orderlistorderprice,orderlistadditionalprice,orderlistcolor;
        CheckBox chkadd,checkall;
        ItemClickListener itemClickListener;
        CardView btncancelorder;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            orderlistname=(TextView) itemView.findViewById(R.id.oderlist_Name);
            orderlistordername=(TextView)itemView.findViewById(R.id.oderlistorderName);
            orderlistadditional=(TextView)itemView.findViewById(R.id.orderlistadditional);
            orderlistdatebook=(TextView)itemView.findViewById(R.id.orderlistdatebook);
            orderlistorderprice=(TextView)itemView.findViewById(R.id.orderlistorderprice);
            orderlistadditionalprice=(TextView)itemView.findViewById(R.id.orderlistadditionalprice);
            orderlistcolor=(TextView)itemView.findViewById(R.id.orderlistcolor);
            chkadd=(CheckBox)itemView.findViewById(R.id.orderlistchkboxadd);
            checkall=(CheckBox)itemView.findViewById(R.id.orderlistcheckall);
            btncancelorder=(CardView) itemView.findViewById(R.id.orderlistcancel);
            chkadd.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener ic) {
            this.itemClickListener=ic;
        }
        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClick(v,getLayoutPosition());
        }
    }



    public void selectAll(){
        isSelectedAll=true;
        notifyDataSetChanged();
    }
    public void unselectall(){
        isSelectedAll=false;
        notifyDataSetChanged();
    }

}
