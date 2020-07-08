package com.example.myapps.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.myapps.R;
import com.example.myapps.DatabaseClass.*;

import java.util.List;


public class CustomerListAdapter extends ArrayAdapter<AddListDatabaseClassR>{
    private Activity context;
    private List<AddListDatabaseClassR> customerlist;
    private List<PaymentDatabaseClass>customerpayment;
    private List<UpdatedOrUpdateClass>customerupdateorupdated;


   public CustomerListAdapter(Activity context, List<AddListDatabaseClassR>customerlist, List<PaymentDatabaseClass>customerpayment) /*List<UpdatedOrUpdateClass>_customerupdateorupdated)*/{
        super(context, R.layout.list_item, customerlist);
        this.context = context;
        this.customerlist = customerlist;
        this.customerpayment=customerpayment;
      //  this.customerupdateorupdated=_customerupdateorupdated;

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem=inflater.inflate(R.layout.list_item,null,true);

        TextView textViewName=(TextView)listViewItem.findViewById(R.id.textListName);
        TextView textViewOrder=(TextView)listViewItem.findViewById(R.id.textListOrder);
        TextView textViewBalance=(TextView)listViewItem.findViewById(R.id.textListBalance);
        TextView textViewBal=(TextView)listViewItem.findViewById(R.id.textListbal);

        AddListDatabaseClassR addListDatabaseClassR=customerlist.get(position);
       try {
           PaymentDatabaseClass paymentDatabaseClass = customerpayment.get(position);
           textViewName.setText(addListDatabaseClassR.get_Name());
           textViewOrder.setText(addListDatabaseClassR.get_Order());

           if (paymentDatabaseClass.get_Balance() <= 0) {
               textViewBalance.setText("Paid");
               textViewBalance.setTextColor(0xFF00FF00);
           } else {
               textViewBalance.setText(String.format("%.2f",paymentDatabaseClass.get_Balance()));
           }
       }catch (IndexOutOfBoundsException e){
//           PaymentDatabaseClass paymentDatabaseClass = customerpayment.get(position);
//           textViewName.setText(addListDatabaseClassR.get_Name());
//           textViewOrder.setText(addListDatabaseClassR.get_Order());

//           if (paymentDatabaseClass.get_Balance() <= 0) {
 //              textViewBalance.setText("Paid");
 //              textViewBalance.setTextColor(0xFF00FF00);
 //          } else {
  //             textViewBalance.setText(String.format("%.2f",paymentDatabaseClass.get_Balance()));
    //       }
       }

           textViewBal.setText("Balance");
           return listViewItem;

    }


}
