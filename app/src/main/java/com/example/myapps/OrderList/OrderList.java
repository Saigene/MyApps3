package com.example.myapps.OrderList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapps.Adapter.OrderlistAdapter;
import com.example.myapps.Adapter.PlaceOrderListAdapter;
import com.example.myapps.DatabaseClass.AddListDatabaseClass;
import com.example.myapps.DatabaseClass.AddListDatabaseClassR;
import com.example.myapps.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OrderList extends Fragment {
    DatabaseReference customerlistreference;
    DatabaseReference customerlistreleased;
    DatabaseReference customerrelease;

    RecyclerView recyclerView;
    ArrayList<AddListDatabaseClassR> customerorderlist = new ArrayList<>();
    List<AddListDatabaseClassR> itemlist = new ArrayList<>();
    OrderlistAdapter orderlistAdapter;
    CheckBox checkall;
    CardView placeorder, release;
    StringBuffer sb=null;
    ListView listitemview;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.orderlist_fragment,container,false);
        customerlistreference= FirebaseDatabase.getInstance().getReference("Customer Pending");
        customerlistreleased=FirebaseDatabase.getInstance().getReference("Customer Info");
        customerrelease=FirebaseDatabase.getInstance().getReference("Customer Info");

        recyclerView=(RecyclerView)v.findViewById(R.id.myrecycleview);
        checkall=(CheckBox)v.findViewById(R.id.orderlistcheckall);
        placeorder=(CardView)v.findViewById(R.id.cardviewplaceorder);
        release=(CardView)v.findViewById(R.id.cardviewrelease);
        listitemview = (ListView) v.findViewById(R.id.orderlistitemall);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        customerlistreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                customerorderlist.clear();
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    AddListDatabaseClassR addListDatabaseClassR = dataSnapshot1.getValue(AddListDatabaseClassR.class);
                        customerorderlist.add(addListDatabaseClassR);
                }
                orderlistAdapter = new OrderlistAdapter(getActivity(), customerorderlist);

                checkall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(checkall.isChecked()){
                            orderlistAdapter.selectAll();
                        }else{
                            orderlistAdapter.unselectall();
                        }
                    }
                });

                recyclerView.setAdapter(orderlistAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        release.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(orderlistAdapter.checkcustomerlist.size()==0){
                    Toast.makeText(getActivity(), "please check the box", Toast.LENGTH_LONG).show();
                }else{
                    AlertDialog.Builder dialogBuilder  =new AlertDialog.Builder(getActivity())
                            .setMessage("Are you sure you want to release his/her order?")
                            .setCancelable(false)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    for (AddListDatabaseClassR addListDatabaseClassR:orderlistAdapter.checkcustomerlist){

                                        customerrelease.child(addListDatabaseClassR.get_Id()).setValue(addListDatabaseClassR);

                                        DatabaseReference deletepending= FirebaseDatabase.getInstance().getReference("Customer Pending").child(addListDatabaseClassR.get_Id());
                                        deletepending.removeValue();
                                    }

                                    Toast.makeText(getActivity(), "Successful", Toast.LENGTH_LONG).show();
                                }
                            })
                            .setNegativeButton(android.R.string.cancel,null);
                    AlertDialog alertDialog=dialogBuilder.create();
                    alertDialog.show();
                }

            }
        });

        placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sb=new StringBuffer();
                itemlist.clear();
                for (AddListDatabaseClassR addListDatabaseClassR:orderlistAdapter.checkcustomerlist){
                    sb.append(addListDatabaseClassR.get_Order());
                    sb.append("\n");
                    itemlist.add(addListDatabaseClassR);
                }
                if(orderlistAdapter.checkcustomerlist.size()>0){
                 //   Toast.makeText(getActivity(),sb.toString(),Toast.LENGTH_SHORT).show();
                    showitemlistselected();
                }else {
                    Toast.makeText(getActivity(), "please check the box", Toast.LENGTH_LONG).show();
                }

            }
        });
        return v;
    }

    private void showitemlistselected(){
        AlertDialog.Builder dialogBuilder  = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View dialogview = inflater.inflate(R.layout.orderlist_item_all,null);
        dialogBuilder.setView(dialogview)
                .setCancelable(false);
        ListView listitemview=(ListView) dialogview.findViewById(R.id.orderlistitemall);
        Button backbtn=(Button)dialogview.findViewById(R.id.backorderlistitem);

        PlaceOrderListAdapter placeOrderListAdapter = new PlaceOrderListAdapter(getActivity(),itemlist);
        listitemview.setAdapter(placeOrderListAdapter);
            Log.i("text2",itemlist.toString());
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }


    public List<AddListDatabaseClassR> getItemlist(){
        return itemlist;
    }



}

