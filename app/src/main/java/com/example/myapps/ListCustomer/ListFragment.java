package com.example.myapps.ListCustomer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapps.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.example.myapps.Adapter.CustomerListAdapter;
import com.example.myapps.DatabaseClass.*;
import com.example.myapps.ListCustomer.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {
    ListView listviewCustomer;

    private DatabaseReference databaselistcustomer;
    private DatabaseReference databasepayment;
    private DatabaseReference databasetextupdate;
//    private DatabaseReference databasedetailpay;

    List<AddListDatabaseClassR> customerlist = new ArrayList<>();
    List<PaymentDatabaseClass> customerpayment = new ArrayList<>();
    List<UpdatedOrUpdateClass> customerupdateorupadated = new ArrayList<>();
 //   List<DetailPayDatabaseClass> customerdetailpay = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.list_fragment,container,false);
        listviewCustomer = v.findViewById(R.id.CustomerList);

        databaselistcustomer = FirebaseDatabase.getInstance().getReference("Customer Info");
        databasepayment = FirebaseDatabase.getInstance().getReference("Customer Payment");
        databasetextupdate = FirebaseDatabase.getInstance().getReference("Customer status");
//        databasedetailpay = FirebaseDatabase.getInstance().getReference("Customer Detail");



        listviewCustomer.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final PaymentDatabaseClass paymentDatabaseClass = customerpayment.get(position);
                final AddListDatabaseClassR addListDatabaseClassR=customerlist.get(position);
                final UpdatedOrUpdateClass updatedOrUpdateClass=customerupdateorupadated.get(position);

                AlertDialog.Builder dialogBuilder  =new AlertDialog.Builder(getActivity())
                            .setMessage("Are you sure you want to delete this Customer")
                            .setCancelable(false)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.i("Delete","Item");
                                DatabaseReference deletecustomerinfo = FirebaseDatabase.getInstance().getReference("Customer Info").child(addListDatabaseClassR.get_Id());
                                DatabaseReference deletecustomerpayment = FirebaseDatabase.getInstance().getReference("Customer Payment").child(paymentDatabaseClass.get_Uid());
                                DatabaseReference deletecustomerstatus=FirebaseDatabase.getInstance().getReference("Customer status").child(updatedOrUpdateClass.get_Uid());
                                DatabaseReference deletecustomerdetail=FirebaseDatabase.getInstance().getReference("Customer Detail Pay").child(updatedOrUpdateClass.get_Uid());
                                deletecustomerinfo.removeValue();
                                deletecustomerpayment.removeValue();
                                deletecustomerstatus.removeValue();
                                deletecustomerdetail.removeValue();
                            }
                            })
                            .setNegativeButton(android.R.string.cancel,null);
                AlertDialog alertDialog=dialogBuilder.create();
                alertDialog.show();
                return false;
            }
        });




        listviewCustomer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

          //          UpdatedOrUpdateClass updatedOrUpdateClass=customerupdateorupadated.get(position);
           //         PaymentDatabaseClass paymentDatabaseClass=customerpayment.get(position);
                    AddListDatabaseClassR addListDatabaseClassR =customerlist.get(position);
  //                  DetailPayDatabaseClass detailPayDatabaseClass=customerdetailpay.get(position);

                    Intent intent=(new Intent(getActivity(),UpdateCustomerListFragment.class));
                    Bundle bundle = new Bundle();
        /*            Log.i("test","test try");
                     bundle.putString("Keydetailpay", detailPayDatabaseClass.getDetailpay());
                    bundle.putString("Keydetaldate", detailPayDatabaseClass.getDetaildatepay());
                    bundle.putString("Keymid1update",updatedOrUpdateClass.get_1midtextupdated());
                    bundle.putString("Keyend1update",updatedOrUpdateClass.get_1endtextupdated());
                    bundle.putString("Keymid2update",updatedOrUpdateClass.get_2midtextupdated());
                    bundle.putString("Keyend2update",updatedOrUpdateClass.get_2endtextupdated());
                    bundle.putString("Keymid3update",updatedOrUpdateClass.get_3midtextupdated());
                    bundle.putString("Keyend3update",updatedOrUpdateClass.get_3endtextupdated());
                    bundle.putString("Keymid4update",updatedOrUpdateClass.get_4midtextupdated());
                    bundle.putString("Keyend4update",updatedOrUpdateClass.get_4endtextupdated());
                    bundle.putString("Keyupdateid",updatedOrUpdateClass.get_Uid())
                    bundle.putDouble("Keypmid1",paymentDatabaseClass.get_1midpayment());
                    bundle.putDouble("Keypend1",paymentDatabaseClass.get_1endpayment());
                    bundle.putDouble("Keypmid2",paymentDatabaseClass.get_2midpayment());
                    bundle.putDouble("Keypend2",paymentDatabaseClass.get_2endpayment());
                    bundle.putDouble("Keypmid3",paymentDatabaseClass.get_3midpayment());
                    bundle.putDouble("Keypend3",paymentDatabaseClass.get_3endpayment());
                    bundle.putDouble("Keypmid4",paymentDatabaseClass.get_4midpayment());
                    bundle.putDouble("Keypend4",paymentDatabaseClass.get_4endpayment());
                    bundle.putDouble("KeyBalance",paymentDatabaseClass.get_Balance());
                    bundle.putDouble("Keysukli",paymentDatabaseClass.get_Zsukli());
                    bundle.putDouble("Keykulang",paymentDatabaseClass.get_Zkulang());
                    bundle.putString("Keypuid",paymentDatabaseClass.get_Uid());
*/
                    bundle.putString("Keyupdatename", addListDatabaseClassR.get_Name());
                    bundle.putString("Keyupdatecpno",addListDatabaseClassR.get_Cpno());
                    bundle.putString("keyupdateshift",addListDatabaseClassR.get_Shift());
                    bundle.putString("Keyupdateorder",addListDatabaseClassR.get_Order());
                    bundle.putString("Keyupdateorderprice",addListDatabaseClassR.get_Orderprice());
                    bundle.putString("Keymonthstopay",addListDatabaseClassR.get_Monthstopay());
                    bundle.putString("Keyadditional",addListDatabaseClassR.get_Additional());
                    bundle.putString("Keyadditionalprice",addListDatabaseClassR.get_AdditionalPrice());
                    bundle.putString("Keytotalprice",addListDatabaseClassR.get_TotalPrice());
                    bundle.putString("Keybalance",addListDatabaseClassR.get_LBalance());
                    bundle.putString("Keytotalpricewithin",addListDatabaseClassR.get_LBalance());
                    bundle.putString("Keydownpayment",addListDatabaseClassR.get_Downpayment());
                    bundle.putString("Keytotalpricewoless",addListDatabaseClassR.get_Totalpricewoless());

                    bundle.putString("Keymid1date",addListDatabaseClassR.get_Date1Mid1());
                    bundle.putString("Keyend1date",addListDatabaseClassR.get_Date1End1());
                    bundle.putString("Keymid2date",addListDatabaseClassR.get_Date2Mid2());
                    bundle.putString("Keyend2date",addListDatabaseClassR.get_Date2End2());
                    bundle.putString("Keymid3date",addListDatabaseClassR.get_Date3Mid3());
                    bundle.putString("Keyend3date",addListDatabaseClassR.get_Date3End3());
                    bundle.putString("Keymid4date",addListDatabaseClassR.get_Date4Mid4());
                    bundle.putString("Keyend4date",addListDatabaseClassR.get_Date4End4());

                    bundle.putString("Keymid1pay",addListDatabaseClassR.get_1Mid1());
                    bundle.putString("Keyend1pay",addListDatabaseClassR.get_1End1());
                    bundle.putString("Keymid2pay",addListDatabaseClassR.get_2Mid2());
                    bundle.putString("Keyend2pay",addListDatabaseClassR.get_2End2());
                    bundle.putString("Keymid3pay",addListDatabaseClassR.get_3Mid3());
                    bundle.putString("Keyend3pay",addListDatabaseClassR.get_3End3());
                    bundle.putString("Keymid4pay",addListDatabaseClassR.get_4Mid4());
                    bundle.putString("Keyend4pay",addListDatabaseClassR.get_4End4());
                    bundle.putString("KeyId",addListDatabaseClassR.get_Id());
                    intent.putExtras(bundle);
                    startActivity(intent);
            }
        });


        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        databaselistcustomer.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                customerlist.clear();
                for(DataSnapshot customerdatasnapshot: dataSnapshot.getChildren()){
                    AddListDatabaseClassR addListDatabaseClassR = customerdatasnapshot.getValue(AddListDatabaseClassR.class);
                        customerlist.add(addListDatabaseClassR);
                }
                if(getActivity()!=null) {
                    CustomerListAdapter customerListAdapter = new CustomerListAdapter(getActivity(), customerlist, customerpayment);/*, customerpayment, customerupdateorupadated);*/
                    Log.i("Listtext",customerlist.toString());
                    listviewCustomer.setAdapter(customerListAdapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databasepayment.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                customerpayment.clear();
                for (DataSnapshot customerdatasnapshot1: dataSnapshot.getChildren()){
                    PaymentDatabaseClass paymentDatabaseClass = customerdatasnapshot1.getValue(PaymentDatabaseClass.class);
                    customerpayment.add(paymentDatabaseClass);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databasetextupdate.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                customerupdateorupadated.clear();
                for (DataSnapshot customerdatasnapshot2: dataSnapshot.getChildren()){
                    UpdatedOrUpdateClass updatedOrUpdateClass = customerdatasnapshot2.getValue(UpdatedOrUpdateClass.class);
                    customerupdateorupadated.add(updatedOrUpdateClass);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

 /*   public static UpdatedOrUpdateClass getupdatedorupdatedclass(UpdatedOrUpdateClass updatedOrUpdateClass){
        UpdatedOrUpdateClass updatedOrUpdateClass=customerupdateorupadated.get(position);
    }*/
}
