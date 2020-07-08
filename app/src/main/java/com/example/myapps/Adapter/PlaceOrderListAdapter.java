package com.example.myapps.Adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.example.myapps.DatabaseClass.AddListDatabaseClass;
import com.example.myapps.DatabaseClass.AddListDatabaseClassR;
import com.example.myapps.DatabaseClass.DetailPayDatabaseClass;
import com.example.myapps.OrderList.OrderList;
import com.example.myapps.R;

import java.util.ArrayList;
import java.util.List;

public class PlaceOrderListAdapter extends ArrayAdapter<AddListDatabaseClassR> {

    Activity context;
    public List<AddListDatabaseClassR> customerlist;

    public PlaceOrderListAdapter(Activity context, List<AddListDatabaseClassR>customerlist){
        super(context, R.layout.orderlist_item, customerlist);
        this.context=context;
        this.customerlist=customerlist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem=inflater.inflate(R.layout.orderlist_item,null,true);
        TextView textorderlistname= (TextView)listViewItem.findViewById(R.id.orderlistitem);
        TextView textorderlistcolor=(TextView)listViewItem.findViewById(R.id.orderlistitemcolor);
        TextView textorderlistadd=(TextView)listViewItem.findViewById(R.id.orderlistitemadd);


        AddListDatabaseClassR addListDatabaseClassR=customerlist.get(position);;
        textorderlistname.setText(addListDatabaseClassR.get_Order());
        textorderlistadd.setText(addListDatabaseClassR.get_Additional());
        textorderlistcolor.setText(addListDatabaseClassR.get_Color());
        return listViewItem;
    }
}
